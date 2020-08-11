$(function(){

    $('#ck-agree').on('change', function(){
        let check = $('#ck-agree');

        if(check[0].checked){
            $('#submit').prop('disabled', false);
            $('#submit') = "agree";
        }else{
            $('#submit').prop('disabled', true);
            $('#submit') = "not-agree";
        }
        window.alert($('#agree'));
    });
    
});