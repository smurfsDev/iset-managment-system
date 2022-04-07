<template>
    <div class="card" style="width: 18rem;">
            <img src="dgwd" class="card-img-top" alt="...">
            <div class="card-body">
              <h5 class="card-title">Card title</h5>
              <p class="card-text">{{headers.petiteDescription}}</p>
              <a href="#" class="btn btn-primary">Go somewhere</a>
            </div>
    </div>
</template>
<script>
export default {
    name: "cardHeader",
    props: {
        header: {
            type: Object,
            required: true
        }
    },
    
    data() {
        return {
           headers:[]
        }
    },
    methods: {
        AddHeader(header){
            this.headers.push(header)
            console.log(this.header)
        }
    }
        
}
</script>
