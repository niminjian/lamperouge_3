<template>
  <div>
    <div class="line1">
      <div class="part1">
        <div id="main01" style="width: 600px; height: 400px"></div>
      </div>
      <div class="part2">
        <div id="main02" style="width: 600px; height: 400px"></div>
      </div>
    </div>
    <div class="line1">
      <div class="part3">
        <div id="main03" style="width: 600px; height: 400px"></div>
      </div>
      <div class="part4">
        <div id="main04" style="width: 600px; height: 400px"></div>
      </div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";
export default {
  name: "list10",
  mounted() {
    var myChart01 = echarts.init(document.getElementById("main01"));
    // 绘制图表
    myChart01.setOption({
      toolbox: {
        show: true,
        feature: {
          mark: { show: true },
          saveAsImage: { show: true },
        },
      },
      title: {
        text: "考试成绩分布情况",
      },
      tooltip: {},
      xAxis: {
        data: ["90+", "80~90", "70~80", "60~70", "不及格"],
      },
      yAxis: {},
      series: [
        {
          name: "分数",
          type: "bar",
          data: [5, 20, 30, 40, 10],
        },
      ],
    });

    var myChart02 = echarts.init(document.getElementById("main02"));
    myChart02.setOption({
      toolbox: {
        show: true,
        feature: {
          saveAsImage: { show: true },
        },
      },
      title: {
        text: "题型正确人数",
      },
      legend: {
        data: ["题型正确人数"],
      },
      radar: {
        // shape: 'circle',
        indicator: [
          { name: "选择题", max: 100 },
          { name: "填空题", max: 100 },
          { name: "判断题", max: 100 },
          { name: "综合题", max: 100 },
          { name: "解析几何", max: 100 },
          { name: "级数", max: 100 },
          { name: "线性代数", max: 100 },
         
        ],
      },
      series: [
        {
          name: "Budget vs spending",
          type: "radar",
          data: [
            {
              value: [85, 75, 90, 80, 80, 80, 80],
              name: "正确人数",
            }
        
          ],
        },
      ],
    });
    var myChart03 = echarts.init(document.getElementById("main03"));
    // 绘制图表
    myChart03.setOption({
      toolbox: {
        show: true,
        feature: {
          mark: { show: true },

          saveAsImage: { show: true },
        },
      },
      title: {
        text: "题目正确率情况",
      },
      tooltip: {},
      yAxis: {
        data: ["T1", "T2", "T3", "T4", "T5"],
      },
      xAxis: {},
      series: [
        {
          name: "正确率%",
          type: "bar",
          data: [80, 60, 70, 40, 20],
        },
      ],
    });

    var myChart04 = echarts.init(document.getElementById("main04"));
    // 绘制图表
    myChart04.setOption(
      {
        title: {
          text: "考试时长分布",
        },
        toolbox: {
          show: true,
          feature: {
            mark: { show: true },
            saveAsImage: { show: true },
          },
        },
        tooltip: {
          trigger: "item",
        },
        legend: {
          top: "5%",
          left: "center",
        },
        series: [
          {
            name: "考试时长",
            type: "pie",
            radius: ["40%", "70%"],
            avoidLabelOverlap: false,
            itemStyle: {
              borderRadius: 10,
              borderColor: "#fff",
              borderWidth: 2,
            },
            label: {
              show: false,
              position: "center",
            },
            emphasis: {
              label: {
                show: true,
                fontSize: "20",
                fontWeight: "bold",
              },
            },
            labelLine: {
              show: false,
            },
            data: [
              { value: 5, name: "30~45mins" },
              { value: 20, name: "45~60mins" },
              { value: 32, name: "60~75mins" },
              { value: 40, name: "75~90mins" },
            ],
          },
        ],
      }
      //   legend: {
      //     top: "bottom",
      //   },
      //   title: {
      //     text: "考试时长分布",
      //   },
      //   toolbox: {
      //     show: true,
      //     feature: {
      //       mark: { show: true },
      //       dataView: { show: true, readOnly: false },
      //       restore: { show: true },
      //       saveAsImage: { show: true },
      //     },
      //   },
      //   series: [
      //     {
      //       name: "Nightingale Chart",
      //       type: "pie",
      //       radius: [50, 180],
      //       center: ["50%", "50%"],
      //       roseType: "area",
      //       itemStyle: {
      //         borderRadius: 8,
      //       },
      //       data: [
      //         { value: 5, name: "30~45mins" },
      //         { value: 20, name: "45~60mins" },
      //         { value: 32, name: "60~75mins" },
      //         { value: 40, name: "75~90mins" },
      //       ],
      //     },
      //   ],
    );
  },
};
</script>

<style scoped lang="scss">
.line1 {
  margin: 20px;
  width: auto;
  height: 500px;
  background-color: #fff;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
