<template>
  <article id="projets">
    <div class="blog-post" id="custom-cards">
      <h2 class="pb-2 border-bottom">Nos projets</h2>

      <div class="row row-cols-1 row-cols-lg-3 align-items-stretch g-4 py-5">
        <div class="col">
          <div
            class="card card-cover h-100 overflow-hidden text-white rounded-5 shadow-lg item"
          >
            <div
              class="d-flex flex-column h-100 p-5 pb-3 text-white text-shadow-1"
            ></div>
          </div>
        </div>

        <div class="col">
          <div
            class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg item"
          >
            <div
              class="d-flex flex-column h-100 p-5 pb-3 text-white text-shadow-1"
            ></div>
          </div>
        </div>

        <div class="col">
          <div
            class="card card-cover h-100 overflow-hidden text-white bg-dark rounded-5 shadow-lg item"
          >
            <div class="d-flex flex-column h-100 p-5 pb-3 text-shadow-1">
              <h2 class="pt-5 mt-5 mb-4 display-6 lh-1 fw-bold">
                Another longer title belongs here
              </h2>
              <ul class="d-flex list-unstyled mt-auto">
                <li class="me-auto">
                  <img
                    src=""
                    alt="Bootstrap"
                    width="32"
                    height="32"
                    class="rounded-circle border border-white"
                  />
                </li>
                <li class="d-flex align-items-center me-3">
                  <small></small>
                </li>
                <li class="d-flex align-items-center">
                  <svg class="bi me-2" width="1em" height="1em">
                    <use xlink:href="#calendar3" />
                  </svg>
                  <small></small>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </article>
</template>

<script>
export default {
  name: "projets",
};
</script>
