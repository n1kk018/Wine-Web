var j = jQuery.noConflict();
if (j.cookie("theme_csspath")) {
	j('link#theme-stylesheet').attr("href", j.cookie("theme_csspath"));
}

j(function() {

	animations();
	productDetailGallery(4000);
	carousels();
	utils();
	j('#currency option[value='+lastCurrency+']').prop('selected','selected');
	if(lastCurrency!="EUR") {
		switchCurrencyClass();
	}
	j('#currency').on('change', function() {
		convertPrices(this.value); 
	});
	//demo();
});


j(window).load(function() {
	j(this).alignElementsSameHeight();
});

j(window).resize(function() {
	setTimeout(function() {
		j(this).alignElementsSameHeight();
	}, 150);
});

function switchCurrencyClass() {
	var tableau = {
			"EUR" : "flaticon-euro-currency-symbol",
	        "GBP" : "flaticon-pound-symbol-variant",
	        "USD" : "flaticon-dollar-currency-symbol-2",
	        "JPY" : "flaticon-yen-currency-symbol",
	        "BGN" : "flaticon-bulgaria-lev",
	        "DKK" : "flaticon-denmark-krone-currency-symbol",
	        "EEK" : "flaticon-estonia-kroon-currency-symbol",
	        "HUF" : "flaticon-hungary-forint-currency-symbol",
	        "LVL" : "flaticon-latvia-lat",
	        "LTL" : "flaticon-lithuania-litas-currency-symbol",
	        "PLN" : "flaticon-poland-zloty-currency-symbol",
	        "CZK" : "flaticon-czech-republic-koruna-currency-symbol",
	        "SEK" : "flaticon-sweden-krona-currency-symbol"
	};
	j("span.currency-symbol").removeClass().addClass("currency-symbol "+tableau[lastCurrency]);
}

function convertPrices(trgtCurrency) {
	srcCurrency = lastCurrency;
	lastCurrency = trgtCurrency;
	rc([{name:'trgt', value:trgtCurrency}]);
	j.ajaxSetup({
		  contentType: "application/json; charset=utf-8"
	});
	j.each(j('.itemPrice'), function(i, o) {
		var element = j(this);
		var amount = j(this).text();
		amount = amount.trim();
		j.ajax({
	        type:"GET",
	        url : "http://192.168.102.42:8180/OnWine-CurrenciesWS-Web/rest/converter/convertAndFormat",
	        dataType: 'json',
	        crossDomain: true,
	        async: false,
	        data: {"amount":amount.replace(",","."),"src":srcCurrency,"trgt":trgtCurrency}
	        
	    }).success(function(response) {
            element.text(response.amount);
            element.next("span.currency-symbol").removeClass().addClass("currency-symbol "+response.currency_class);
        });
	});
}

/* product detail gallery */

function productDetailGallery(confDetailSwitch) {
	j('.thumb:first').addClass('active');
	timer = setInterval(autoSwitch, confDetailSwitch);
	j(".thumb").click(function(e) {

		switchImage(j(this));
		clearInterval(timer);
		timer = setInterval(autoSwitch, confDetailSwitch);
		e.preventDefault();
	}
	);
	j('#mainImage').hover(function() {
		clearInterval(timer);
	}, function() {
		timer = setInterval(autoSwitch, confDetailSwitch);
	});

	function autoSwitch() {
		var nextThumb = j('.thumb.active').closest('div').next('div').find('.thumb');
		if (nextThumb.length == 0) {
			nextThumb = j('.thumb:first');
		}
		switchImage(nextThumb);
	}

	function switchImage(thumb) {

		j('.thumb').removeClass('active');
		var bigUrl = thumb.attr('href');
		thumb.addClass('active');
		j('#mainImage img').attr('src', bigUrl);
	}
}

function utils() {
	WebFont.load({
	    google: {
	      families: ['Roboto:400,500,700,300,100']
	    }
	  });

	/* click on the box activates the radio */

	j('#checkout').on('click', '.box.shipping-method, .box.payment-method', function(e) {
		var radio = j(this).find(':radio');
		radio.prop('checked', true);
	});
	/* click on the box activates the link in it */

	j('.box.clickable').on('click', function(e) {

		window.location = j(this).find('a').attr('href');
	});
	/* external links in new window*/

	j('.external').on('click', function(e) {

		e.preventDefault();
		window.open(j(this).attr("href"));
	});
	/* animated scrolling */

	j('.scroll-to, .scroll-to-top').click(function(event) {

		var full_url = this.href;
		var parts = full_url.split("#");
		if (parts.length > 1) {

			scrollTo(full_url);
			event.preventDefault();
		}
	});
	function scrollTo(full_url) {
		var parts = full_url.split("#");
		var trgt = parts[1];
		var target_offset = j("#" + trgt).offset();
		var target_top = target_offset.top - 100;
		if (target_top < 0) {
			target_top = 0;
		}

		j('html, body').animate({
			scrollTop: target_top
		}, 1000);
	}
}

/* carousels */

function carousels() {

	j("#get-inspired").owlCarousel({
		navigation: true, // Show next and prev buttons
		slideSpeed: 300,
		paginationSpeed: 400,
		autoPlay: true,
		stopOnHover: true,
		singleItem: true,
		afterInit: ''
	});

	j('.product-slider').owlCarousel({
		navigation: true, // Show next and prev buttons
		slideSpeed: 300,
		paginationSpeed: 400,
		afterInit: function() {
			j('.product-slider .item').css('visibility', 'visible');
		}
	});

	j('#main-slider').owlCarousel({
		navigation: true, // Show next and prev buttons
		slideSpeed: 300,
		paginationSpeed: 400,
		autoPlay: true,
		stopOnHover: true,
		singleItem: true,
		afterInit: ''
	});

}

/* animations */

function animations() {
	delayTime = 0;
	j('[data-animate]').css({opacity: '0'});
	j('[data-animate]').waypoint(function(direction) {
		delayTime += 150;
		j(this).delay(delayTime).queue(function(next) {
			j(this).toggleClass('animated');
			j(this).toggleClass(j(this).data('animate'));
			delayTime = 0;
			next();
			//j(this).removeClass('animated');
			//j(this).toggleClass(j(this).data('animate'));
		});
	},
	{
		offset: '90%',
		triggerOnce: true
	});

	j('[data-animate-hover]').hover(function() {
		j(this).css({opacity: 1});
		j(this).addClass('animated');
		j(this).removeClass(j(this).data('animate'));
		j(this).addClass(j(this).data('animate-hover'));
	}, function() {
		j(this).removeClass('animated');
		j(this).removeClass(j(this).data('animate-hover'));
	});

}

j.fn.alignElementsSameHeight = function() {
	j('.same-height-row').each(function() {

		var maxHeight = 0;

		var children = j(this).find('.same-height');

		children.height('auto');

		if (j(document).width() > 768) {
			children.each(function() {
				if (j(this).innerHeight() > maxHeight) {
					maxHeight = j(this).innerHeight();
				}
			});

			children.innerHeight(maxHeight);
		}

		maxHeight = 0;
		children = j(this).find('.same-height-always');

		children.height('auto');

		children.each(function() {
			if (j(this).innerHeight() > maxHeight) {
				maxHeight = j(this).innerHeight();
			}
		});

		children.innerHeight(maxHeight);

	});

}


