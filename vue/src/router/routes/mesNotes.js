const mesNotes = () =>
  import(
    "../../components/etudiant/mesNotes/mesNotes.vue"
  );
export default [
  {
    path: "/mesNotes",
    component: mesNotes,
    name: "mesNotes",
    meta: { requiresStudent: true},
  },
];
