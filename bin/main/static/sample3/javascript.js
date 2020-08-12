$(function(){

    $('#agree').on('change', function(){
        let check = $('#agree');

        if(check[0].checked){
            $('#submit').prop('disabled', false);
        }else{
            $('#submit').prop('disabled', true);
        }
    });

    //タブ切り替え
    $('#tab-new-application').on('click', function(){
        $('#new-application').show();
        $('#inquiry-payment').hide();
        $('#chenge-procedure').hide();
        $('#stop-payment').hide();

        $('#tab-new-application').addClass('tab-active');
        $('#tab-inquiry-payment').removeClass('tab-active');
        $('#tab-chenge-procedure').removeClass('tab-active');
        $('#tab-stop-payment').removeClass('tab-active');
    });
    $('#tab-inquiry-payment').on('click', function(){
        $('#new-application').hide();
        $('#inquiry-payment').show();
        $('#chenge-procedure').hide();
        $('#stop-payment').hide();

        $('#tab-new-application').removeClass('tab-active');
        $('#tab-inquiry-payment').addClass('tab-active');
        $('#tab-chenge-procedure').removeClass('tab-active');
        $('#tab-stop-payment').removeClass('tab-active');
    });
    $('#tab-chenge-procedure').on('click', function(){
        $('#new-application').hide();
        $('#inquiry-payment').hide();
        $('#chenge-procedure').show();
        $('#stop-payment').hide();

        $('#tab-new-application').removeClass('tab-active');
        $('#tab-inquiry-payment').removeClass('tab-active');
        $('#tab-chenge-procedure').addClass('tab-active');
        $('#tab-stop-payment').removeClass('tab-active');
    });
    $('#tab-stop-payment').on('click', function(){
        $('#new-application').hide();
        $('#inquiry-payment').hide();
        $('#chenge-procedure').hide();
        $('#stop-payment').show();

        $('#tab-new-application').removeClass('tab-active');
        $('#tab-inquiry-payment').removeClass('tab-active');
        $('#tab-chenge-procedure').removeClass('tab-active');
        $('#tab-stop-payment').addClass('tab-active');
    });
    
});