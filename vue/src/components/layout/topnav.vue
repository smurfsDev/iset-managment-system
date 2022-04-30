<template>
    <sidebar-menu class="position-fixed" @item-click='onItemClick' :show-one-child="true" @toggle-collapse="collapse = !collapse"
        :width="'200px'" :menu="
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
                    href: '/Entreprise',
                    title: 'Entreprise',
                    icon: 'fas fa-building',
                    hidden: !this.isAdmin
                },
                {
                    href: '/etudiant/demandeCreationClub',
                    title: 'Demande creation club',
                    icon: 'fas fa-plus',
                    hidden: !this.isStudent
                },
                {
                    href: '/admin/demandeCreationClub',
                    title: 'Demande creation club',
                    icon: 'fas fa-building',
                    hidden: !this.isAdmin
                },
                {
                    //href: '/dashboard',
                    title: 'Dashboard blog club',
                    icon: 'fas fa-dashboard',
                     child: [
                        {
                            href: '/headerDash',
                            title: 'Entete',
                            hidden: !this.isStudent
                        },
                        {
                            href: '/aboutDash',
                            title: 'Qui sommes nous?',
                            hidden: !this.isStudent
                        },
                        {
                            href: '/activitiesDash',
                            title: 'Activités',
                            hidden: !this.isStudent
                        },
                        {
                            href: '/boardDash',
                            title: 'Membres de bureau',
                            hidden: !this.isStudent
                        },
                        {
                            href: '/projectsDash',
                            title: 'Projets',
                            hidden: !this.isStudent
                        },

                    ]
                    //hidden: !this.isStudent
                },
        
            ]
        " :collapsed="false" style="transition: 0.5s max-width ease!important;" />
</template>

<script>
import { mapGetters } from 'vuex';
export default {
    data() {
        return {
            collapse: true,
            menu: [
                {
                    header: 'Main Navigation',
                    hiddenOnCollapse: true
                },
                {
                    title: 'User',
                    icon: 'fas fa-user',
                    child: [
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
                            href: '/logout',
                            title: 'Logout',
                            hidden: !this.isAuth
                        },
                    ]
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
                    href: '/logout',
                    title: 'Logout',
                    hidden: !this.isAuth
                },
                {
                    href: '/',
                    title: 'Home',
                    icon: 'fas fa-home',
                },
                {
                    href: '/Entreprise',
                    title: 'Entreprise',
                    icon: 'fas fa-building',
                },

            ]
        }
    },
    updated() {
    },
    methods: {
        onItemClick(event, item) {
            if (item.title=="Logout") {
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
            student : 'isStudent',
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

    }
}
</script>