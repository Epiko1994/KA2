var navbar = '\
<div class="container">\
  <a class="navbar-brand" href="index.html">JJU Group</a>\
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">\
    <span class="navbar-toggler-icon"></span>\
  </button>\
  <div class="collapse navbar-collapse" id="navbarResponsive">\
    <ul class="navbar-nav ml-auto">\
      <li class="nav-item">\
            <a class="nav-link" href="../KA2/openapi">API Doc</a>\
        </li>\
        <li class="nav-item">\
        <a class="nav-link" href="https://github.com/Epiko1994/KA2">Github Repo</a>\
      </li>\
    </ul>\
  </div>\
</div>'

document.getElementById("nav").innerHTML=navbar;