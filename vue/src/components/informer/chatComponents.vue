<template>
    <div class="col-md-8">
        <div class="card">
            <div class="card-header">Chats</div>
            <div class="card-body">
                <chatMessagesComponent></chatMessagesComponent>
                <chatFormComponent></chatFormComponent>
            </div>
        </div>
    </div>
</template>

<script>
import chatMessagesComponent from './chatMessagesComponent';
import chatFormComponent from "./chatFormComponent";

    export default {
        components: {
        chatMessagesComponent,
        chatFormComponent,
        },
        mounted() {
            console.log('Component mounted.')
        }
    }
</script>