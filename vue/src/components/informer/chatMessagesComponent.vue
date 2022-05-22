<template>
    <div class="message-area">
        <messageComponent></messageComponent>
    </div>
</template>

<script>
    import messageComponent from './MessageComponent.vue'
    export default {
        components: {
            messageComponent,
        },
        mounted() {
            console.log('Component mounted.')
        }
    }
    
</script>
<style>
    .message-area {
        height: 400px;
        max-height: 400px;
        overflow-y: scroll;
        padding: 15px;
        border-bottom: 1px solid #eee;
    }
</style>