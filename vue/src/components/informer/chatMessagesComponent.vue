<template>
    <div class="message-area">
            <!-- {{userId}} -->

        <messageComponent
            v-for="message in messages" 
            :key="message.id" 
            :message="message"
        
        >
</messageComponent>
    </div>
</template>

<script>
    import messageComponent from './MessageComponent.vue'
    export default {
        components: {
            messageComponent,
        },
         data() {
            return {
                // messages: [],
            }
        },
        props: {
            // userId: Number,
            messages:Array,
        },
        mounted() {
            console.log('Component mounted.')
        },
        created() {
        },
        methods: {
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