'use strict';

$(function(){
   const $form = $("#form");
   const $button = $("#register");

   $button.click(function() {
           // reCAPTCHA v3用
           grecaptcha.ready(function () {
               grecaptcha.execute('6LeytwIkAAAAAOoKJJcCGFRkBoJFoVN6XuHJatQV', {action: 'contact_form'}).then(function(token) {
                   $('#recaptchaResponse').val(token);

                   $form.submit();
               });
           });
   });
});