<template>
  <div>
    <div
      class="card card-body my-5 py-5 text-center"
      v-if="Students.length == 0"
    >
      <h3>il y'a aucune Student</h3>
    </div>
    <b-card class="my-2" v-for="Student in Students" :key="Student.id">
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'success',
          title: 'Student accepted',
          placement: 'topright',
        }"
        v-if="Student.status == 1"
        icon="check-square"
        scale="2"
        variant="success"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'danger',
          title: 'Student not accepted',
          placement: 'topright',
        }"
        v-if="Student.status == 2"
        icon="x-square"
        scale="2"
        variant="danger"
      ></b-icon>
      <b-icon
        class="float-end"
        v-b-tooltip.hover="{
          variant: 'warning',
          title: 'Student pending',
          placement: 'topright',
        }"
        v-if="Student.status == 0"
        icon="x-square"
        scale="2"
        variant="warning"
      ></b-icon>
      <md-tabs style="height: auto;!important">
        <md-tab
          style="height: auto;!important"
          id="tab-home"
          md-label="Student"
        >
          <div class="bv-example-row text-center">
            <b-row class="mb-2">
              <b-row>
                <b-col>
                  Nom  : {{ Student.name }} 
                </b-col>
              </b-row>
              <b-row>
                <b-col>Email : {{ Student.email }}</b-col>
              </b-row>
            </b-row>
            <b-button
              variant="success"
              :class="[{ disabled: status(Student.roles) == 1 }]"
              v-on:click="Accept(Student.id)"
              >Accept</b-button
            >
            <b-button
              variant="danger"
              :class="[{ disabled: status(Student.roles) == 2 }]"
              v-on:click="Decline(Student.id)"
              >Decline</b-button
            >
          </div>
        </md-tab>
      </md-tabs>
    </b-card>
    <nav class="row" v-if="Students.length != 0">
      <ul class="pagination w-auto mx-auto">
        <li
          :class="[{ disabled: !pagination.prev_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchStudent(pagination.prev_page_url)"
            class="btn btun page-link"
            :class="[
              !pagination.prev_page_url ? 'disabled' : 'link-primary btun',
            ]"
            >Precedent</a
          >
        </li>
        <li class="page-item">
          <a class="page-link text-dark" href="#">
            {{ pagination.current_page + "/" + pagination.last_page }}
          </a>
        </li>
        <li
          :class="[{ disabled: !pagination.next_page_url }]"
          class="page-item"
        >
          <a
            @click="fetchStudent(pagination.next_page_url)"
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
  props: {
    Students: Array,
    pagination: Object,
  },
  data() {
    return {
      id: "",
    };
  },
  emits: ["acceptStudent", "declineStudent", "fetchStudent"],
  methods: {
    Accept(id) {
      this.$emit("acceptStudent", id);
    },
    Decline(id) {
      this.$emit("declineStudent", id);
    },
    fetchStudent(url) {
      this.$emit("fetchStudent", url);
    },
    status(roles){
      let stat = 0 ;
      roles.forEach((r)=>{
        if (r.pivot.role_id == 2){
          stat = r.pivot.status
        }
      })
      return stat;
    }
  },
};
</script>

<style></style>
