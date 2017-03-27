$(document).ready(function(){

   $(".alphabeth-button").click(function(e){
        e.preventDefault();

        var value = this.value;

        console.log("You clicked button " + value);
   });
});