<template>
    <div>
        <form class="form">
            <textarea 
                v-on:keyup.enter="saveMessage()"
                v-model="body"
                cols="25"
                rows="5"
                class="form-input">
            </textarea>
            <span class="notice">
                Hit Return to send a message 
            </span>
        </form>
    </div>
</template>

<script>
    export default {
        props: {
            messages:Array,
            userId: Number,
        },
        data() {
            return {
                body: '',
            }
        },
        mounted() {
            console.log('Component mounted.')
        },
        methods: {
            saveMessage(){
                this.$http.post("http://localhost:8000/api/message/store/"+this.userId,
                {
                body:this.body
                }).then(response => {
                    this.messages = response.data;
                    this.body = '';
                })
            }
        }
    }
</script>
<style>
    .form {
        padding: 8px;
    }
    .form-input {
        width: 100%;
        border: 1px solid #d3e0e9;
        padding: 5px 10px;
        outline: none;
    }
    .notice {
        color: #aaa
    }
    
</style>