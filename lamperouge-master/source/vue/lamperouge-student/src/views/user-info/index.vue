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

            <el-tab-pane label="专业书籍总览" name="coarseBook">
              <div id="main04" style="width: 700px; height: 700px"></div>
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
                  <el-button type="primary"  @click="faceLogin">人脸采集</el-button>
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

    var myChart04 = echarts.init(document.getElementById("main04"));
    const colors = ['#ff5770', '#FF7853', '#c21111', '#CC3F57', '#9A2555'];
    const bgColor = '#fdfcfd';
    const itemStyle = {
      star5: {
        color: colors[0]
      },
      star4: {
        color: colors[1]
      },
      star3: {
        color: colors[2]
      },
      star2: {
        color: colors[3]
      }
    };
    const data = [
      {
        name: '应用',
        itemStyle: {
          color: colors[1]
        },
        children: [
          {
            name: '前端',
            children: [
              {
                name: '5☆',
                children: [
                  {
                    name: 'JavaScript高级程序设计'
                  },
                  {
                    name: 'Vue实战'
                  },
                  {
                    name: 'JavaScript权威指南'
                  }
                ]
              },
              {
                name: '4☆',
                children: [
                  {
                    name: 'ES6标准入门'
                  },
                  {
                    name: 'JavaScript编程实战'
                  },
                  {
                    name: 'JavaScript基础教程'
                  }
                ]
              },
              {
                name: '3☆',
                children: [
                  {
                    name: 'JavaScript快速开发'
                  }
                ]
              }
            ]
          },
          {
            name: '后端',
            children: [
              {
                name: '5☆',
                children: [
                  {
                    name: 'Java核心技术'
                  }
                ]
              },
              {
                name: '4☆',
                children: [
                  {
                    name: 'Java编程思想'
                  },
                  {
                    name: '深入理解Java虚拟机'
                  }
                ]
              },
              {
                name: '3☆',
                children: [
                  {
                    name: '垃圾回收算法与实践'
                  }
                ]
              }
            ]
          }
        ]
      },
      {
        name: '原理',
        itemStyle: {
          color: colors[2]
        },
        children: [
          {
            name: '计算机网络',
            children: [
              {
                name: '5☆',
                children: [
                  {
                    name: '网络是怎么连接的'
                  }
                ]
              },
              {
                name: '4☆',
                children: [
                  {
                    name: '计算机网络：自顶向下'
                  },
                  {
                    name: '图解 TCP/IP'
                  }
                ]
              },
              {
                name: '3☆',
                children: [
                  {
                    name: '计算机网络-自顶向下'
                  }
                ]
              }
            ]
          },
          {
            name: '操作系统',
            children: [
              {
                name: '5☆',
                children: [
                  {
                    name: '实用操作系统概念'
                  }
                ]
              },
              {
                name: '4☆',
                children: [
                  {
                    name: '操作系统概念'
                  },
                  {
                    name: '操作系统：现代观点'
                  },
                  {
                    name: '现代操作系统'
                  }
                ]
              },
              {
                name: '3☆',
                children: [
                  {
                    name: 'Linux 内核情景分析'
                  }
                ]
              }
            ]
          },
          {
            name: '计算机组成',
            children: [
              {
                name: '5☆',
                children: [
                  {
                    name: '计算机组成原理'
                  }
                ]
              },
              {
                name: '4☆',
                children: [
                  {
                    name: '信息简史'
                  },
                  {
                    name: '人工智能简史'
                  }
                ]
              },
              {
                name: '3☆'
              },
              {
                name: '2☆',
                children: [
                  {
                    name: '黑客与画家'
                  }
                ]
              }
            ]
          },
          {
            name: '计算机系统',
            children: [
              {
                name: '5☆',
                children: [
                  {
                    name: '计算机系统原理'
                  }
                ]
              },
              {
                name: '4☆',
                children: [
                  {
                    name: '编译原理'
                  },
                  {
                    name: '深入理解计算机系统'
                  }
                ]
              },
              {
                name: '3☆',
                children: [
                  {
                    name: '设计模式'
                  }
                ]
              }
            ]
          },
        ]
      }
    ];
    for (let j = 0; j < data.length; ++j) {
      let level1 = data[j].children;
      for (let i = 0; i < level1.length; ++i) {
        let block = level1[i].children;
        let bookScore = [];
        let bookScoreId;
        for (let star = 0; star < block.length; ++star) {
          let style = (function (name) {
            switch (name) {
              case '5☆':
                bookScoreId = 0;
                return itemStyle.star5;
              case '4☆':
                bookScoreId = 1;
                return itemStyle.star4;
              case '3☆':
                bookScoreId = 2;
                return itemStyle.star3;
              case '2☆':
                bookScoreId = 3;
                return itemStyle.star2;
            }
          })(block[star].name);
          block[star].label = {
            color: style.color,
            downplay: {
              opacity: 0.5
            }
          };
          if (block[star].children) {
            style = {
              opacity: 1,
              color: style.color
            };
            block[star].children.forEach(function (book) {
              book.value = 1;
              book.itemStyle = style;
              book.label = {
                color: style.color
              };
              let value = 1;
              if (bookScoreId === 0 || bookScoreId === 3) {
                value = 5;
              }
              if (bookScore[bookScoreId]) {
                bookScore[bookScoreId].value += value;
              } else {
                bookScore[bookScoreId] = {
                  color: colors[bookScoreId],
                  value: value
                };
              }
            });
          }
        }
        level1[i].itemStyle = {
          color: data[j].itemStyle.color
        };
      }
    }
    myChart04.setOption( {
      toolbox: {
        show: true,
        feature: {
          saveAsImage: { show: true },
        },
      },
      backgroundColor: bgColor,
      color: colors,
      series: [
        {
          type: 'sunburst',
          center: ['50%', '48%'],
          data: data,
          sort: function (a, b) {
            if (a.depth === 1) {
              return b.getValue() - a.getValue();
            } else {
              return a.dataIndex - b.dataIndex;
            }
          },
          label: {
            rotate: 'radial',
            color: bgColor
          },
          itemStyle: {
            borderColor: '#FFFF',
            borderWidth: 2
          },
          levels: [
            {},
            {
              r0: 0,
              r: 40,
              label: {
                rotate: 0
              }
            },
            {
              r0: 40,
              r: 105
            },
            {
              r0: 115,
              r: 140,
              itemStyle: {
                shadowBlur: 2,
                shadowColor: colors[2],
                color: 'transparent'
              },
              label: {
                rotate: 'tangential',
                fontSize: 10,
                color: colors[0]
              }
            },
            {
              r0: 140,
              r: 145,
              itemStyle: {
                shadowBlur: 80,
                shadowColor: colors[0]
              },
              label: {
                position: 'outside',
                textShadowBlur: 5,
                textShadowColor: '#333'
              },
              downplay: {
                label: {
                  opacity: 0.5
                }
              }
            }
          ]
        }
      ]
    }
    )
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
