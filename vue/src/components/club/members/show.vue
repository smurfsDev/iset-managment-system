<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="members.length == 0"
    >
      <h3>il y'a aucun member</h3>
    </div>
    <b-card class="my-2" v-for="member in members" :key="member.id">
      <md-tabs>
        <md-tab id="tab-home" md-label="member">
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Nom du membre : {{ member.user.name }}
                </b-col>
              </b-row>
              <b-row>
                <b-col>
                  Club : {{ member.club.nom }}
                </b-col>
              </b-row>
            </b-row>
            <b-button
              variant="danger"
              v-on:click="Delete(member.id)"
              >Delete</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="members.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchMember(pagination.prev_page_url)"
            class="btn btun page-link"
            :class="[
              !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
            ]"
            >Precedent</a
          >
        </li>
        <li class="page-item">
          <a class="page-link text-dark" href="#">{{
            pagination.current_page + "/" + pagination.last_page
          }}</a>
        </li>
        <li
          :class="[{ disabled: !pagination.next_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchMember(pagination.next_page_url)"
            :class="[
              !pagination.next_page_url ? 'disabled' : 'link-primary btun',
            ]"
            class="btun btn page-link"
            >Suivant</a
          >
        </li>
      </ul>
    </nav>
  </div>
</template>

<script>
export default {
    props:{
        members:Array,
        pagination: Object,
    },
    emits:['fetchMember','deleteMember'],
    methods:{
        Delete(id){
            this.$emit("deleteMember",id);
        },
        fetchMember(url){
            this.$emit("fetchMember",url);
        },
    },
}
</script>

<style>

</style>