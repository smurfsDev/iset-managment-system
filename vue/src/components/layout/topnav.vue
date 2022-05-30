<template>
    <sidebar-menu class="position-fixed" @item-click='onItemClick' :show-one-child="true"
        @toggle-collapse="collapse = !collapse" :width="'200px'" :menu="
            [
                {
                    header: 'Main Navigation',
                    hiddenOnCollapse: true
                },
                {
                    title: this.$store.getters.userName,
                    icon: 'fas fa-user',
                    child: [
                        {
                            href: '/user',
                            title: 'User',
                            hidden: !this.isAuth
                        },
                        {
                            href: '/login',
                            title: 'Login',
                            hidden: this.isAuth
                        },
                        {
                            href: '/register',
                            title: 'Register',
                            hidden: this.isAuth
                        },
                        {
                            title: 'Logout',
                            hidden: !this.isAuth
                        },
                    ]
                },
                {
                    href: '/',
                    title: 'Home',
                    icon: 'fas fa-home',
                },
                {
                    href: '/consulterClasseEns',
                    title: 'Classes et Matieres',
                    icon: 'fas fa-home',
                    hidden: !this.isEnseignant
                },
                {
                    href: '/Entreprise',
                    title: 'Entreprise',
                    icon: 'fas fa-building',
                    hidden: !this.isAdmin
                },{
                    href: '/cd/manageStudents',
                    title: 'Students',
                    icon: 'fas fa-user',
                    hidden: !this.isChefDepartement
                },{
                    href: '/manageEnseignant',
                    title: 'Enseignant',
                    icon: 'fas fa-user',
                    hidden: !this.isChefDepartement
                },
                {
                    href: '/cd/manageTechnicien',
                    title: 'Techniciens',
                    icon: 'fas fa-user',
                    hidden: !this.isChefDepartement
                },{
                    href: '/cd/GererClasse',
                    title: 'Classes',
                    icon: 'fas fa-building',
                    hidden: !this.isChefDepartement
                },
                {
                    href: '/etudiant/demandeCreationClub',
                    title: 'Demande creation club',
                    icon: 'fas fa-plus',
                    hidden: !this.isStudent
                },
                
                {
                    href: '/admin/manageChefDepartments',
                    title: 'chefDepartements',
                    icon: 'fas fa-user',
                    hidden: !this.isAdmin
                },
                {
                    //href: '/dashboard',
                    title: 'Dashboard blog club',
                    icon: 'fas fa-dashboard',
                    hidden: !this.isResponsableClub,
                     child: [
                        {
                            href: '/headerDash',
                            title: 'Entete',
                        },
                        {
                            href: '/aboutDash',
                            title: 'Qui sommes nous?',
                        },
                        {
                            href: '/activitiesDash',
                            title: 'Activités',
                        },
                        {
                            href: '/boardDash',
                            title: 'Membres de bureau',
                        },
                        {
                            href: '/projectsDash',
                            title: 'Projets',
                        },

                    ]
                },
                {
                    href: '/listeClubs',
                    title: 'Clubs',
                    icon: 'fas fa-building',
                    child: [
                        {
                            href: '/demandeAdhesionClub',
                            title: 'Mes Demandes d\'adhesion',
                            hidden: !this.isStudent
                        },
                        {
                            href: '/demandeAdhesionResponsable',
                            title: 'Demandes d\'adhesion',
                            hidden: !this.isStudent
                        },
                        {
                            href: '/members',
                            title: 'membres',
                            hidden: !this.isResponsableClub
                        }
                    ]
                },
                {
                    //href: '/listeEvents',
                    title: 'Evenements',
                    icon: 'fas fa-building',
                    hidden: !this.isStudent,
                    child: [
                        
                        {
                            href: '/listeEvents',
                            title: 'Liste des évenements',
                            hidden: !this.isStudent
                        },
                        {
                            href: '/demandeAdhesionEvent',
                            title: 'Mes Demandes d\'adhesion',
                            hidden: !this.isStudent
                        }
                    ]
                },
                {
                    title: 'Demandes',
                    icon: 'fa fa-file',
                    child: [
                        {
                            href: '/admin/demandeCreationClub',
                            title: 'Demande creation club',
                            icon: 'fas fa-building',
                            hidden: !this.isAdmin
                        },
                        {
                            href: 'demandeSalle',
                            title: 'Demande salle',
                            icon: 'fa fa-file',
                            hidden: !this.isStudent && !this.isResponsableClub,
                        },
                        {
                            href: 'demandeMateriel',
                            title: 'Demande materiel',
                            icon: 'fa fa-file',
                            hidden: !this.isStudent && !this.isResponsableClub,
                        },
                        {
                            href: 'demandeEvent',
                            title: 'Plannification évenement',
                            icon: 'fas fa-file',
                            hidden: !this.isResponsableClub
                        },
                        {
                            href: '/admin/demandePlannificationEvent',
                            title: 'Demande évenement',
                            icon: 'fas fa-file',
                            hidden: !this.isAdmin
                        },
                        {
                            href: 'MesDemandesMateriel',
                            title: 'Demande materiel',
                            icon: 'fa fa-file',
                            hidden: !this.isAdmin && !this.isChefDepartement,
                        }, 
                        {
                            href: 'MesDemandesSalle',
                            title: 'Demande Salle',
                            icon: 'fa fa-file',
                            hidden: !this.isAdmin && !this.isChefDepartement,
                        },
                        {
                            href: '/responseDemandeAdhEvent',
                            title: 'Adhesion evenement',
                            icon: 'fas fa-building',
                            hidden: !this.isResponsableClub,
                        },
                    ]
                },
                {
                    title:'Documents',
                    icon: 'fa fa-file',
                    href: 'GererDocument',
                    hidden: !this.isChefDepartement && !this.isStudent
                },

                {
                    title: 'Reclamation',
                    href:'GererReclamation',
                    icon: 'fa fa-file',
                    hidden: !this.isAuth
                },

                 {
                    title: 'DemandeDocument',
                    href:'Gererdemandedocument',
                    icon: 'fa fa-file',
                    hidden: !this.isAuth
                },
                
                {
                    hidden:!this.isAdmin,
                    title: 'Catégorie document',
                    icon: 'fa fa-file',
                    href:'GererCategorie',
                },
                 {
                    hidden:!this.isAdmin,
                    title: 'Type Reclamation',
                    icon: 'fa fa-file',
                    href:'GererTypeCat',
                    href: '/gererCategorieMateriel',
                    title: 'gererCategorieMateriel',
                    icon: 'fas fa-file',
                    hidden: !this.isAdmin && !this.isChefDepartement && !this.isTechnicien,
                },
                {
                    href: '/chat',
                    title: 'Chat',
                    icon: 'fas fa-file',
                    
                },
                {
                    href: '/gererMateriel',
                    title: 'gererMateriel',
                    icon: 'fas fa-file',
                    hidden: !this.isAdmin && !this.isChefDepartement && !this.isTechnicien,
                },
                {
                    href: '/mesNotes',
                    title: 'Mes notes',
                    icon: 'fas fa-file',
                    hidden: !this.isStudent,
                }

            ]
        " :collapsed="false" style="transition: 0.5s max-width ease!important;" />
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    data() {
        return {
            collapse: true,
        }
    },
    updated() {
    },
    methods: {
        onItemClick(event, item) {
            if (item.title == "Logout") {
                this.$store.dispatch('LogOut');
            }
        },
        checkWidth(t = 0) {
            let docs = document.querySelectorAll('.vsm_expanded');
            if (t == 1) {
                if (docs.length == 0) {
                    document.querySelector('#contain').classList.add('contain');
                    document.querySelector('#contain').classList.remove('contain-closed');
                } else {
                    document.querySelector('#contain').classList.remove('contain');
                    document.querySelector('#contain').classList.add('contain-closed');
                }
            } else {
                if (docs.length == 0) {
                    document.querySelector('#contain').classList.remove('contain');
                    document.querySelector('#contain').classList.add('contain-closed');
                } else {
                    document.querySelector('#contain').classList.add('contain');
                    document.querySelector('#contain').classList.remove('contain-closed');
                }
            }
        }
    },
    created() {
        window.document.body.style.marginLeft = this.collapse ? '200px' : '50px'
    },
    watch: {
        collapse(val) {
            window.document.body.style.marginLeft = val ? '200px' : '50px'
        }
    },
    computed: {
        ...mapGetters({
            auth: 'isAuthenticated',
            admin: 'isAdmin',
            student: 'isStudent',
            responsable: 'isResponsableClub',
            chefDepartement: 'isChefDepartement',
            technicien: 'isTechnicien',
            enseignant: 'isEnseignant'
        }),
        isAuth: function () {
            return this.auth;
        },
        isAdmin: function () {
            return this.admin;
        },
        isStudent: function () {
            return this.student;
        },
        isResponsableClub: function () {
            return this.responsable;
        },
        isChefDepartement: function () {
            return this.chefDepartement;
        },
        isTechnicien: function () {
            return this.technicien;
        },
        isEnseignant: function(){
            return this.enseignant;
        }


    }
}
</script>