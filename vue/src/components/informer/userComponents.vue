<template>
     <div class="col-md-4">
        <div class="card">
            <div class="card-header">Users</div>
            <div class="card-body">
                <div class="users" v-for="user in users" :key="user.id">
                    <a @click="submitMessage(user.id)">{{ user.name }}</a>
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
            }
        },
        emits: ["messages"],
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
            submitMessage(id) {
                this.$http.get("http://localhost:8000/api/message/M/"+id).then(response => {
                this.messages = response.data;
                this.$emit('messages', response.data);
                })
                .catch(() => {
                    this.messages = [];
                    this.$emit('messages', []);
                });
            }
        }

    }
</script>
<style>
    .users {
        background-color: #fff;
        border-radius: 3px;
    }
</style>