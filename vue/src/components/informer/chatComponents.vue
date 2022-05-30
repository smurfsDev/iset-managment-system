<template>
    <div class="col-md-8">
        <div class="card">
            <div class="card-header">Chats</div>
            <div class="card-body">
                <chatMessagesComponent  :messages="messages"></chatMessagesComponent>
                <chatFormComponent :messages="messages" :userId="userId"></chatFormComponent>
            </div>
        </div>
    </div>
</template>

<script>
import chatMessagesComponent from './chatMessagesComponent';
import chatFormComponent from "./chatFormComponent";

    export default {
        emits: ["fetchThem"],
        components: {
        chatMessagesComponent,
        chatFormComponent,
        },
        mounted() {
            console.log('Component mounted.')
        },
        updated() {
            this.$emit("fetchThem");
        },
        props: {
            userId: Number,
            messages:Array,
        },
        data() {
            return {
                user: {},
            }
        },
        created() {
        },
        methods: {
           
        }
    }
</script>