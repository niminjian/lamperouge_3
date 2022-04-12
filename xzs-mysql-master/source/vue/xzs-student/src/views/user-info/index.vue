<template>
  <div style="margin-top: 10px" class="app-contain">
    <el-row :gutter="50">
      <el-col :span="17">
        <el-card shadow="hover">
          <el-tabs active-name="event" type="card">
            <el-tab-pane label="用户动态" name="event">
              <div class="block">
                <el-timeline>
                  <el-timeline-item
                    :timestamp="item.createTime"
                    placement="top"
                    :key="item.id"
                    v-for="item in event"
                  >
                    <el-card>
                      <p>{{ item.content }}</p>
                    </el-card>
                  </el-timeline-item>
                </el-timeline>
              </div>
            </el-tab-pane>
            <el-tab-pane label="个人资料修改" name="update">
              <el-form
                :model="form"
                ref="form"
                label-width="100px"
                v-loading="formLoading"
                :rules="rules"
              >
                <el-form-item label="真实姓名：" prop="realName" required>
                  <el-input v-model="form.realName"></el-input>
                </el-form-item>
                <el-form-item label="年龄：">
                  <el-input v-model="form.age"></el-input>
                </el-form-item>
                <el-form-item label="性别：">
                  <el-select v-model="form.sex" placeholder="性别" clearable>
                    <el-option
                      v-for="item in sexEnum"
                      :key="item.key"
                      :value="item.key"
                      :label="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="出生日期：">
                  <el-date-picker
                    v-model="form.birthDay"
                    value-format="yyyy-MM-dd"
                    type="date"
                    placeholder="选择日期"
                  />
                </el-form-item>
                <el-form-item label="手机：">
                  <el-input v-model="form.phone"></el-input>
                </el-form-item>
                <el-form-item label="年级：" prop="userLevel" required>
                  <el-select v-model="form.userLevel" placeholder="年级">
                    <el-option
                      v-for="item in levelEnum"
                      :key="item.key"
                      :value="item.key"
                      :label="item.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="submitForm">更新</el-button>
                  <el-button type="primary"  @click="faceLogin">人脸验证</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
      <el-col :span="7">
        <el-card class="cardmy">
          <div slot="header" class="clearfix">
            <span>个人信息</span>
          </div>
          <el-row style="text-align: center">
            <el-upload
              action="/api/student/upload/image"
              accept=".jpg,.png"
              :show-file-list="false"
              :on-success="uploadSuccess"
            >
              <el-avatar
                class="el-dropdown-avatar"
                :size="100"
                :src="
                  form.imagePath === null
                    ? require('@/assets/avatar.png')
                    : form.imagePath
                "
              ></el-avatar>
            </el-upload>
          </el-row>
          <el-row class="user-info-userName">
            <label>{{ form.userName }}</label>
          </el-row>
          <el-divider />
          <el-row class="user-info-fullInfo">
            <label>姓名：{{ form.realName }}</label
            ><br />
            <label>年级：{{ levelFormatter(form.userLevel) }}</label
            ><br />
            <label>注册时间：{{ form.createTime }}</label
            ><br />
          </el-row>
          <h4>我的能力</h4>
          <div id="main02" style="width: 400px; height: 400px"></div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import userApi from "@/api/user";
import { mapGetters, mapState } from "vuex";
import * as echarts from "echarts";
export default {
  data() {
    return {
      event: [],
      form: {
        userName: "",
        realName: "",
        age: "",
        sex: "",
        birthDay: null,
        phone: null,
        userLevel: null,
        createTime: null,
        imagePath: null,
      },
      formLoading: false,
      rules: {
        realName: [
          { required: true, message: "请输入真实姓名", trigger: "blur" },
        ],
        userLevel: [
          { required: true, message: "请选择年级", trigger: "change" },
        ],
      },
    };
  },
  mounted() {
    var myChart02 = echarts.init(document.getElementById("main02"));
    myChart02.setOption({
      toolbox: {
        show: true,
        feature: {
          saveAsImage: { show: true },
        },
      },
    
      legend: {
        data: ["平均能力", "我的能力"],
      },
      radar: {
        // shape: 'circle',
        indicator: [
          { name: "语文", max: 100 },
          { name: "数学", max: 100 },
          { name: "英语", max: 100 },
          { name: "政治", max: 100 },
          { name: "历史", max: 100 },
          { name: "地理", max: 100 },
          { name: "物理", max: 100 },
          { name: "化学", max: 100 },
          { name: "生物", max: 100 },
          { name: "技术", max: 100 },
        ],
      },
      series: [
        {
          name: "Budget vs spending",
          type: "radar",
          data: [
            {
              value: [85, 75, 90, 80, 80, 80, 80, 80, 80, 80],
              name: "平均能力",
            },
            {
              value: [80, 100, 85, 70, 70, 75, 95, 90, 90, 97],
              name: "我的能力",
            },
          ],
        },
      ],
    });
  },
  created() {
    let _this = this;
    userApi.getUserEvent().then((re) => {
      _this.event = re.response;
    });
    userApi.getCurrentUser().then((re) => {
      _this.form = re.response;
    });
  },
  methods: {
    uploadSuccess(re, file) {
      if (re.code === 1) {
        window.location.reload();
      } else {
        this.$message.error(re.message);
      }
    },
    submitForm() {
      let _this = this;
      this.$refs.form.validate((valid) => {
        if (valid) {
          this.formLoading = true;
          userApi
            .update(this.form)
            .then((data) => {
              if (data.code === 1) {
                _this.$message.success(data.message);
              } else {
                _this.$message.error(data.message);
              }
              _this.formLoading = false;
            })
            .catch((e) => {
              _this.formLoading = false;
            });
        } else {
          return false;
        }
      });
    },
    levelFormatter(level) {
      return this.enumFormat(this.levelEnum, level);
    },
    faceLogin() {
      window.location.href = "http://localhost:8000/demo";
    },
  },
  computed: {
    ...mapGetters("enumItem", ["enumFormat"]),
    ...mapState("enumItem", {
      sexEnum: (state) => state.user.sexEnum,
      levelEnum: (state) => state.user.levelEnum,
    }),
  },
};
</script>

<style lang="scss" scoped>
.cardmy{
  width: 425px ;
}

</style>
