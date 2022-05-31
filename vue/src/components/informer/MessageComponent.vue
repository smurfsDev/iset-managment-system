<template>
    <div class="message self"
    :class="{'messagi': message.user.id == $store.getters.StateUser.id}"
    >
        <strong class="user">{{ message.user.name }}</strong>
        <p class="body">{{ message.body }}</p>
    </div>
</template>

<script>
    export default {
    props: {
        message: Object
    },
    
  }
</script>
<style>
    .user {
        font-weight: 800;
    }
    .body {
        margin-bottom: 0;
        white-space: pre-wrap;
    }
    .message {
        border-bottom: 1px solid #ffffff
    }
    .self {
        background-color: #f0f0f0;
        padding: 10px;
    }
    .messagi{
        text-align: right;
    }
</style>