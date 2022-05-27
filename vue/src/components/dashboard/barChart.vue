<script>
import { Bar } from 'vue-chartjs';
export default {
    extends: Bar,
    props: ['labels', 'data','label'],
    mounted() {
        this.renderChart(
            {
                labels: this.labels,
                datasets: [
                    {
                        label: this.label,
                        data: this.data,
                        backgroundColor: [
                            '#ffcdd2',
                            '#f8bbd0',
                            '#d1c4e9',
                            '#c5cae9',
                        ],
                        borderColor: "white",
                        pointBackgroundColor: "white",
                    }
                ]
            },
            {
                responsive: true,
                maintainAspectRatio: false,
                title: {
                    display: true,
                    text: "My Data"
                },
                fontColor: 'red'
            },
        );
    }
};
</script>