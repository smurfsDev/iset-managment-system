<script>
import { Pie } from "vue-chartjs";
export default {
  extends: Pie,
  props: ["labels", "data", "label", "title"],
  data() {
    return {};
  },
  mounted() {
    this.renderChart(
      {
        labels: this.labels,
        datasets: [
          {
            label: this.label,
            data: this.data,
            backgroundColor: ["#41B883", "#E46651", "#00D8FF", "#DD1B16"],
            borderColor: "white",
            pointBackgroundColor: "white",
            width: 20,
          },
        ],
      },
      {
        responsive: true,
        maintainAspectRatio: false,
        title: {
          display: true,
          text: this.title,
        },
      }
    );
  },
};
</script>