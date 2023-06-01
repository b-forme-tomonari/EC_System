$(function () {
  $('.pagenation_block').paginathing({
    perPage: 10,
    activeClass: 'active',
    containerClass: "panel-footer",
    prevNext: false,
  	firstLast: false,
	insertAfter: '.pagination2',
  });
});