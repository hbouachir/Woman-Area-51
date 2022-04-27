jQuery(document).ready(function($){
	"use strict";


	/* ---------------------------------------------------------------------- */
	/*	Prettyphoto
	/* ---------------------------------------------------------------------- */
	if($("[rel^='prettyPhoto']").length){
		$("[rel^='prettyPhoto']").prettyPhoto();
	}

	/* ---------------------------------------------------------------------- */
	/*	ToolTip
	/* ---------------------------------------------------------------------- */
	if($("[data-toggle='tooltip']").length){
		$('[data-toggle="tooltip"]').tooltip();
	}

	/* ---------------------------------------------------------------------- */
	/*	Owl Slider
	/* ---------------------------------------------------------------------- */
	if($("#owl-demo").length){

		var owl = $("#owl-demo");

		owl.owlCarousel({
			itemsCustom : [
				[0, 2],
				[450, 2],
				[700, 3],
				[1000, 5],
				[1200, 5],
				[1360, 6],
			],
			navigation : true
		});
	}
	/* ---------------------------------------------------------------------- */
	/*	Owl Slider
	/* ---------------------------------------------------------------------- */
	if($(".owl-client").length){
		var owl = $(".owl-client");
		owl.owlCarousel({
			itemsCustom : [
				[0, 2],
				[450, 2],
				[700, 3],
				[1000, 4],
				[1200, 4],
				[1360, 4],
			],
			navigation : true
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Owl Slider
	/* ---------------------------------------------------------------------- */
	if($(".owl-banner").length){

		var owl = $(".owl-banner");

		owl.owlCarousel({
			itemsCustom : [
				[0, 2],
				[450, 2],
				[700, 3],
				[1000, 3],
				[1200, 3],
				[1360, 3],
			],
			navigation : true
		});

	}

	/* ---------------------------------------------------------------------- */
	/*	Sticky header
	/* ---------------------------------------------------------------------- */
	if($('.header-style-333').length){
		// grab the initial top offset of the navigation
		//var stickyNavTop = $('#mainbanner').offset().top;
		var stickyNavTop = 40;
		// our function that decides weather the navigation bar should have "fixed" css position or not.
		var stickyNav = function(){
			var scrollTop = $(window).scrollTop(); // our current vertical position from the top
			// if we've scrolled more than the navigation, change its position to fixed to stick to top,
			// otherwise change it back to relative
			if (scrollTop > stickyNavTop) {
				$('.header-style-3').addClass('kf_sticky');
			} else {
				$('.header-style-3').removeClass('kf_sticky');
			}
		};
		stickyNav();
		// and run it again every time you scroll
		$(window).scroll(function() {
			stickyNav();
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Responsive DL Menu
	/* ---------------------------------------------------------------------- */
	if(typeof($.fn.dlmenu) == 'function'){
		$('#kode-responsive-navigation').each(function(){
			$(this).find('.dl-submenu').each(function(){
				if( $(this).siblings('a').attr('href') && $(this).siblings('a').attr('href') != '#' ){
					var parent_nav = $('<li class="menu-item kode-parent-menu"></li>');
					parent_nav.append($(this).siblings('a').clone());

					$(this).prepend(parent_nav);
				}
			});
			$(this).dlmenu();
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Progress Bar
	/* ---------------------------------------------------------------------- */
	if($('.custom-skills .progress .progress-bar').length){
		$('.custom-skills  .progress .progress-bar').progressbar({display_text: 'fill'});
	}

	/* ---------------------------------------------------------------------- */
    /*  Circle Progress
    /* ---------------------------------------------------------------------- */
    if($('.circle-progress').length){
        $('.circle-progress').percentcircle({
          animate : true,
          diameter : 1.0,
          guage: 0.3,
          coverBg: '#fff',
          bgColor: '#efefef',
          fillColor: '#CC3367',
          percentSize: '10px',
        });
    }

	/* ---------------------------------------------------------------------- */
	/*	Scroll To TOp
	/* ---------------------------------------------------------------------- */
	if($("#link").length){
		$("#link").click(function() {
		   scrollToAnchor('id3');
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Accordion Script
	/* ---------------------------------------------------------------------- */
	if($('.accordion').length){
		//custom animation for open/close
		$.fn.slideFadeToggle = function(speed, easing, callback) {
		  return this.animate({opacity: 'toggle', height: 'toggle'}, speed, easing, callback);
		};

		$('.accordion').accordion({
		  defaultOpen: '#section2',
		  cookieName: 'nav',
		  speed: 'slow',
		  animateOpen: function (elem, opts) { //replace the standard slideUp with custom function
			elem.next().stop(true, true).slideFadeToggle(opts.speed);
		  },
		  animateClose: function (elem, opts) { //replace the standard slideDown with custom function
			elem.next().stop(true, true).slideFadeToggle(opts.speed);
		  }
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	CountDown Script
	/* ---------------------------------------------------------------------- */
	if($('#defaultCountdown').length){
		var austDay = new Date();
		austDay = new Date('2022-05-13T12:00:00');
		$('#defaultCountdown').countdown({until: austDay});
		$('#year').text(austDay.getFullYear());
	}

	/* ---------------------------------------------------------------------- */
	/*	Select Menu
	/* ---------------------------------------------------------------------- */
	if($(".select-menu").length){
		$(".select-menu").selectbox();
	}

	/* ---------------------------------------------------------------------- */
	/*	Range Slider
	/* ---------------------------------------------------------------------- */
	if($(".range").length){
		$(".range").slider();
		$(".range").on("slide", function(slideEvt) {
			$(".range-slider").text(slideEvt.value);
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Range Slider
	/* ---------------------------------------------------------------------- */
	if($(".range2").length){
		$(".range2").slider();
		$(".range2").on("slide", function(slideEvt) {
			$(".range-slider2").text(slideEvt.value);
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Bx Slider
	/* ---------------------------------------------------------------------- */
	if($(".bxslider").length){
		$('.bxslider').bxSlider({
			auto:true

		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Bx Slider Thumbnail
	/* ---------------------------------------------------------------------- */
	if($(".bxslider-thums").length){
		$('.bxslider-thums').bxSlider({
		  auto:true,
		  pagerCustom: '#bx-pager'
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Counter
	/* ---------------------------------------------------------------------- */
	if($('.counter').length) {
		$('.counter').counterUp({
			delay: 10,
			time: 1000
		});
	}

	/* ---------------------------------------------------------------------- */
	/*	Contact Form
	/* ---------------------------------------------------------------------- */
	if($('#contactform').length) {

		var $form = $('#contactform'),
		$loader = '<img src="images/ajax_loading.gif" alt="Loading..." />';
		$form.append('<div class="hidden-me" id="contact_form_responce">');

		var $response = $('#contact_form_responce');
		$response.append('<p></p>');

		$form.submit(function(e){

			$response.find('p').html($loader);

			var data = {
				action: "contact_form_request",
				values: $("#contactform").serialize()
			};

			//send data to server
			$.post("inc/contact-send.php", data, function(response) {

				response = $.parseJSON(response);

				$(".incorrect-data").removeClass("incorrect-data");
				$response.find('img').remove();

				if(response.is_errors){

					$response.find('p').removeClass().addClass("error type-2");
					$.each(response.info,function(input_name, input_label) {

						$("[name="+input_name+"]").addClass("incorrect-data");
						$response.find('p').append('Please enter correct "'+input_label+'"!'+ '</br>');
					});

				} else {

					$response.find('p').removeClass().addClass('success type-2');

					if(response.info == 'success'){

						$response.find('p').append('Your email has been sent!');
						$form.find('input:not(input[type="submit"], button), textarea, select').val('').attr( 'checked', false );
						$response.delay(1500).hide(400);
					}

					if(response.info == 'server_fail'){
						$response.find('p').append('Server failed. Send later!');
					}
				}

				// Scroll to bottom of the form to show respond message
				var bottomPosition = $form.offset().top + $form.outerHeight() - $(window).height();

				if($(document).scrollTop() < bottomPosition) {
					$('html, body').animate({
						scrollTop : bottomPosition
					});
				}

				if(!$('#contact_form_responce').css('display') == 'block') {
					$response.show(450);
				}

			});

			e.preventDefault();

		});

	}

	/* ---------------------------------------------------------------------- */
	/*	Countdown
	/* ---------------------------------------------------------------------- */
	if($('.countdown').length){
		$('.countdown').final_countdown({
			'start': 1362139200,
			'end': 1388461320,
			'now': 1387461319
		});
	}

});
/* ---------------------------------------------------------------------- */
/*	Back to Top
/* ---------------------------------------------------------------------- */
function scrollToAnchor(aid){
    var aTag = $("a[name='"+ aid +"']");
    $('html,body').animate({scrollTop: aTag.offset().top},'slow');
}
/* ---------------------------------------------------------------------- */
/*	Google Map Function for Custom Style
/* ---------------------------------------------------------------------- */
function initialize() {
	var MY_MAPTYPE_ID = 'custom_style';
	var map;
	var brooklyn = new google.maps.LatLng(40.6743890, -73.9455);
	var featureOpts = [
		{"featureType":"landscape.man_made","elementType":"geometry","stylers":[{"color":"#f7f1df"}]},{"featureType":"landscape.natural","elementType":"geometry","stylers":[{"color":"#d0e3b4"}]},{"featureType":"landscape.natural.terrain","elementType":"geometry","stylers":[{"visibility":"off"}]},{"featureType":"poi","elementType":"labels","stylers":[{"visibility":"off"}]},{"featureType":"poi.business","elementType":"all","stylers":[{"visibility":"off"}]},{"featureType":"poi.medical","elementType":"geometry","stylers":[{"color":"#fbd3da"}]},{"featureType":"poi.park","elementType":"geometry","stylers":[{"color":"#bde6ab"}]},{"featureType":"road","elementType":"geometry.stroke","stylers":[{"visibility":"off"}]},{"featureType":"road","elementType":"labels","stylers":[{"visibility":"off"}]},{"featureType":"road.highway","elementType":"geometry.fill","stylers":[{"color":"#ffe15f"}]},{"featureType":"road.highway","elementType":"geometry.stroke","stylers":[{"color":"#efd151"}]},{"featureType":"road.arterial","elementType":"geometry.fill","stylers":[{"color":"#ffffff"}]},{"featureType":"road.local","elementType":"geometry.fill","stylers":[{"color":"black"}]},{"featureType":"transit.station.airport","elementType":"geometry.fill","stylers":[{"color":"#cfb2db"}]},{"featureType":"water","elementType":"geometry","stylers":[{"color":"#a2daf2"}]}

	];
	var mapOptions = {
		zoom: 12,
		center: brooklyn,
		mapTypeControlOptions: {
			mapTypeIds: [google.maps.MapTypeId.ROADMAP, MY_MAPTYPE_ID]
		},
		mapTypeId: MY_MAPTYPE_ID
	};

	map = new google.maps.Map(
		document.getElementById('map-canvas'),
		mapOptions
	);

	var styledMapOptions = {
		name: 'Custom Style'
	};

	var customMapType = new google.maps.StyledMapType(featureOpts, styledMapOptions);

	map.mapTypes.set(MY_MAPTYPE_ID, customMapType);
}
