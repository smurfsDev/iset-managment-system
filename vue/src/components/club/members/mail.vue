<template>
  <div
    class="modal fade"
    id="MailModal"
    tabindex="-1"
    aria-labelledby="MailModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="MailModal">Envoi Email</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
            @click="hideModal('MailModal')"
          ></button>
        </div>

        <form class="mb-3" @submit.prevent="send">
          <div class="modal-body">
            <div class="form-group mb-2">
                <label>Sujet :</label>
              <div class="form-group">
                <input type="text" class="form-control" v-model="sujet" placeholder="sujet"  required="required" />
              </div>
                <label>Message :</label>
                <textarea  class="form-control" v-model="message" placeholder="Message"  required="required" />
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
              @click="hideModal('MailModal')"
            >
              Fermer
            </button>
            <button type="submit" class="btn btn-primary">Sauvegarder</button>
          </div>
        </form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  emits: ["sendMail"],
    props: {
    use: Array,
    },
    data() {
    return {
        sujet: '',
        message: '',

    }
    },
  methods: {
        sendMail(id){
            this.$emit("sendMail",id);
        },
        send(){
            for(var i=0; i < this.use.length;i++){

                this.sendMail(this.use[i]);
            }
        }
  },
};
</script>

<style></style>
