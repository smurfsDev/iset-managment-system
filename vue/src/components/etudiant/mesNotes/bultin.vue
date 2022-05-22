<template>
  <html lang="en">
    <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <title>Bultin de note</title>
    </head>

    <body>
      <div
        style="
          width: 1300;
          height: auto;
          padding: 20px;
          text-align: center;
          border: 10px solid #787878;
        "
      >
        <div
          style="
            width: 1240;
            height: auto;
            padding: 20px;
            text-align: center;
            border: 5px solid #787878;
          "
        >
          <span style="font-size: 25px; font-weight: bold">
            <img
              width="100"
              height="100"
              src="data:image/jpeg;base64,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"
            />
            <br />institut superieur des etudes technologique de bizerte</span
          ><br /><br />
          <span style="font-size: 30px; font-weight: bold">Relevé de note</span
          ><br />
          <br />
          <!-- <span style="font-size:25px"><i>Congrats !!</i></span> -->
          <br />
          <span style="font-size: 20px"
            ><b>Nom et prenom: {{ userName }}</b></span
          ><br /><br />
          <span style="font-size: 20px"
            ><b>Class : {{ notes[0].matiere.classe.nom }} </b></span
          ><br /><br />
          <span style="font-size: 20px"
            ><b>Niveau : {{ 
            notes[0].matiere.classe.nom.charAt(notes[0].matiere.classe.nom.length - 2)
            }} </b></span
          ><br /><br />

          <table align="center" border="12"  class="table table-hover table-bordered">
            <thead>
              <tr>
                <th width="500" class="tg-0pky">Numero de relevé:</th>
                <th class="text-center" colspan="3">{{
                 numBul }}</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th  class="text-center" width="240" colspan="4">Matières</th>
              </tr>
              <tr>
                <td width="500">Nom</td>
                <td width="500">Coefficient</td>
                <td width="500">Note</td>
                <td width="500">Enseignant</td>
              </tr>
              <tr v-for="note in notes" :key="note.id">
                <td >{{ note.matiere.nom }}</td>
                <td >{{ note.matiere.coefficient }}</td>
                <td >{{ note.note }}</td>
                <td >{{ note.matiere.enseignant.name }}</td>
              </tr>
              
              <tr>
                <th width="240" class="text-center"  colspan="4">Resultat final</th>
              </tr>
              <tr>
                <td width="500" align="left" colspan="2">Sommes des notes :</td>
                <td
                  width="500"
                  align="right"
                  style="padding-right: 25px"
                  colspan="2"
                >
                  {{ somme }}
                </td>
              </tr>
              <tr>
                <td width="500" align="left" colspan="2">Coefficient :</td>
                <td
                  width="500"
                  align="right"
                  style="padding-right: 25px"
                  colspan="2"
                >
                  {{ nb }}
                </td>
              </tr>
              <tr>
                <td width="500" align="left" colspan="2">Moyenne :</td>
                <td
                  width="500"
                  align="right"
                  style="padding-right: 25px"
                  colspan="2"
                >
                  {{ myMoy }}
                </td>
              </tr>
            </tbody>
          </table>
          <span style="font-size: 25px"
            ><i>Date : </i
            >{{
              today
            }}</span
          ><br />
        </div>
      </div>
    </body>
  </html>
</template>

<script>
export default {
  props: ["userName", "Clazz", "notes", "numBul"],
  computed: {
    myMoy() {
      let somme = 0;
      let nb = 0;
      for (let i = 0; i < this.notes.length; i++) {
        somme += this.notes[i].note * this.notes[i].matiere.coefficient;
        nb += this.notes[i].matiere.coefficient;
      }
      return (somme / nb).toFixed(2);
    },
    somme() {
      let somme = 0;
      for (let i = 0; i < this.notes.length; i++) {
        somme += this.notes[i].note * this.notes[i].matiere.coefficient;
      }
      return somme;
    },
    nb() {
      let nb = 0;
      for (let i = 0; i < this.notes.length; i++) {
        nb += this.notes[i].matiere.coefficient;
      }
      return nb;
    },
    today() {
      let today = new Date();
      // format date Day/Month/Year Hour:Minute:Second
      return (
        today.getDate() +
        "/" +
        (today.getMonth() + 1) +
        "/" +
        today.getFullYear() +
        " " +
        // get day in week
        today.toLocaleString("fr-FR", {
          weekday: "long"
        }) +
        " " +
        today.getHours() +
        ":" +
        today.getMinutes() +
        ":" +
        today.getSeconds()
      );
    },
  },
};
</script>

<style>
</style>