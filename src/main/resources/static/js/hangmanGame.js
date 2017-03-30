$(document).ready(function(){

   $(".alphabeth-button").click(function(e){
        e.preventDefault();

        var gameId = $('#gameId').data('game-id')
        var value = this.value;
        var guessedWord = $('#guessedWord').val();

        console.log("You clicked button " + value);

        $.post({
            url: "/game/" + gameId + '/' + value,
            success: function(data){
                console.log("Response from server:: #guessedWord => " + data.existingGuessedLetters);
//                $('#guessedWord').val(data.existingGuessedLetters);
                $('#guessedWord').html(data.existingGuessedLetters);
                $('#numRemainingIncorrectGuesses').html(data.numRemainingIncorrectGuesses);
                $('#gameState').html(data.gameState);
            }
        });
   });
});