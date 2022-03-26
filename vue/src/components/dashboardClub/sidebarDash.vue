<template>
    <div>
        <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-white sidebar collapse">
      <div class="position-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item light">
            <a class="nav-link active" aria-current="page" href="/dashboard">
              <span data-feather="home"></span>
              Dashboard
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="file"></span>
             > Blog club
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/headerDash">
              <span data-feather="shopping-cart"></span>
              En tete
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="users"></span>
              Qui sommes-nous ?
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="bar-chart-2"></span>
              Activités
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="layers"></span>
              Membres de bureau 
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#">
              <span data-feather="layers"></span>
              Projets 
            </a>
          </li>
        </ul>
    </div>
        
        
    </nav>
    </div>
</template>

<script>
export default {
    name: "sidebarDash"
}
</script>
