<template>
  <div>
    <div class="content container">
      <div class="pt-3 pb-3 container-fluid">
        <b-overlay v-if="show" :show="show" class="d-inline-block" style="height: 500px; width: 100%"></b-overlay>
        <div v-if="!show">
          <b-container class="bv-example-row py-0">
            <b-row class="text-center">
              <b-col cols="8">
               
              </b-col>
              <b-col></b-col>
            </b-row>
          </b-container>
          <b-alert class="mt-4" :show="alert.dismissCountDown" dismissible :variant="alert.variant"
            @dismissed="alert.dismissCountDown = 0">
            <p>{{ alert.msg }}</p>
          </b-alert>

          <!-- <b-card> -->
          <showNotes @deleteNote="deleteNote" :Notes="Notes"
            @fetchNote="fetchNotes" @updateNote="updateNote" :pagination="pagination" />
          <!-- </b-card> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import showNotes from "./show.vue";
// import search from '../search.vue';

export default {
  components: {
    showNotes,
    //     search
  },
  data() {
    return {
      Notes: [],
      Note: {},
      pagination: {},
      edit: false,
      search: "",
      show: true,
      alert: {
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
      err:'',

    };
  },
  created() {
    // console.log(typeof $);
    document.title = "Note";
    this.fetchNotes();
    if (this.$route.params.add == 1) {
      this.alert.variant = "success";
      this.alert.msg = "Note ajouté avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.edit == 1) {
      this.alert.variant = "warning";
      this.alert.msg = "Note modifié avec succès";
      this.alert.dismissCountDown = 5;
    } else if (this.$route.params.add == 2) {
      this.alert.variant = this.$route.params.alert.variant;
      this.alert.msg = this.$route.params.alert.msg;
      this.alert.dismissCountDown = 5;
    }
  },
  methods: {
    fetchNotes(
      page_url = "http://127.0.0.1:8000/api/note/getMyNotes/",alert={
        dismissCountDown: 0,
        variant: "",
        msg: "",
      },
    ) {
      let vm = this;
      this.$http.get(page_url)
      .then((res)=> {
        this.Notes = res.data;
        this.show = false;
        this.edit=false;
        this.alert=alert
        vm.makePagination(res.data);
      });
    },
    makePagination(meta) {
      this.pagination = {
        current_page: meta.current_page,
        current_page_url:
          "http://localhost:8000/api/note/getMyNotes?page=" + meta.current_page,
        last_page: meta.last_page,
        next_page_url: meta.next_page_url,
        prev_page_url: meta.prev_page_url,
      };
    },
    deleteNote(id) {
      if (confirm("Delete document " + id)) {
        this.show = true;
        this.$http.delete("http://localhost:8000/api/note/getMyNotes/" + id)
        .then(() => {
          this.fetchNotes();
            this.alert.variant = "danger";
            this.alert.msg = "Note suprimée avec succès";
            this.alert.dismissCountDown = 5;
        });
      }
    },
    resetModal1() {
      this.document = {};
    },
  
    updateNote(Note) {
      this.edit = true;
      this.Note = Note;
    },
    searchEmploye(search) {
      this.search = search;
      this.fetchNotes();
    },
    initModal() {
      this.Note = {};
      this.showModal("NoteModal");
    },
  },
  computed: {
    haveClub() {
      // check if at least one Notes have status 1
      return this.Notes.some((Notes) => {
        return Notes.status == 1;
      });
    },
  },
};
</script>

<style>
.btun {
  color: white !important;
  background-color: #d32f2f !important;
}
</style>
