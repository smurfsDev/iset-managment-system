<template>
     <div class="col-md-4">
        <div class="card">
            <div class="card-header">Contacts</div>
            <div class="card-body">
                <div class="users" v-for="user in users" :key="user.id">
                    <a @click="fetchMyMessages(user.id)">{{ user.name }}</a>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                users: [],
                messages: [],
                myid: 0,
            }
        },
        emits: ["messages","fetchMyMessages"],
        mounted() {
            console.log('Component mounted.')
        },
        created() {
            this.fetchUsers();
        },
        methods: {
            fetchUsers() {
                this.$http.get("http://localhost:8000/api/message/users").then(response => {
                    this.users = response.data;
                })
            },
            fetchMyMessages(id){
                this.myid = id;
                this.$emit("fetchMyMessages",id);
            }
            
            
        }

    }
</script>
<style>
    .users {
        background-color: rgb(227, 227, 227);
        border-bottom: 1px solid #a9a9a9;
        padding: 3px;
        padding-left: 10px;
    }
    .users:hover {
        background-color: #737373;
        color: white;
    }
</style>