var cp = {
  el: {},
  fn: {
    goLink: function (url) {
      var isInternalLink = (url[0] === '/');
      if (typeof window.CampuspickWebview !== 'undefined') {
        if (isInternalLink) {
          window.CampuspickWebview.open(location.origin + url, false);
        } else {
          window.CampuspickWebview.open(url, true);
        }
      } else {
        if (isInternalLink) {
          location.href = url;
        } else {
          window.open(url, '_blank');
        }
      }
    },
    goBack: function () {
      if (typeof window.CampuspickWebview !== 'undefined') {
        window.CampuspickWebview.close(false);
      } else {
        history.go(-1);
      }
    },
    goBackAndRefresh: function () {
      if (typeof window.CampuspickWebview !== 'undefined') {
        window.CampuspickWebview.close(true);
      } else {
        history.go(-1);
      }
    },
    getCommunityUrl: function (url) {
      if (location.pathname === '/community' && location.search !== '') {
        var params = {};
        var a = location.search.substr(1).split('&');
        _.each(a, function (i) {
          var b = i.split('=');
          if (b.length === 2) {
            params[b[0]] = b[1];
          }
        });
        if (params.id) {
          url = '/community?id=' + params.id + '&url=' + encodeURIComponent(url);
        }
      }
      return url;
    },
    getPageCache: function (location) {
      if (typeof Storage === 'undefined' || $('body').hasClass('app')) {
        return null;
      }
      try {
        var key = 'page_' + location.pathname + location.search;
        var value = sessionStorage.getItem(key);
        var data = JSON.parse(value);
        return data;
      } catch (e) {
        return null;
      }
    },
    setPageCache: function (location, data) {
      if (typeof Storage === 'undefined' || $('body').hasClass('app')) {
        return;
      }
      try {
        var key = 'page_' + location.pathname + location.search;
        var value = JSON.stringify(data);
        sessionStorage.setItem(key, value);
      } catch (e) {
      }
    },
    formatNumber: function (text) {
      return text.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
    },
    formatPrice: function (text) {
      return text.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',') + '원';
    },
    formatDate: function (text) {
      var date = '';
      var year = Number(text.slice(0, 4));
      var nowYear = new Date().getFullYear();
      if (year !== nowYear) {
        date += year.toString() + '년 ';
      }
      date += Number(text.slice(5, 7)).toString() + '월 ' + Number(text.slice(8, 10)).toString() + '일';
      var weekArray = ['일', '월', '화', '수', '목', '금', '토'];
      date += '(' + weekArray[new Date(text).getDay()] + ')';
      return date;
    },
    formatArticleDate: function (text) {
      var year = Number(text.slice(0, 4));
      var nowYear = new Date().getFullYear();
      var date = text.slice(5, 7) + '/' + text.slice(8, 10) + ' ' + text.slice(11, 16);
      if (year !== nowYear) {
        date = text.slice(2, 4) + '/' + date;
      }
      return date;
    },
    formatArticleShortDate: function (text) {
      var yearString = text.slice(0, 4);
      var monthString = text.slice(5, 7);
      var dayString = text.slice(8, 10);
      var year = Number(yearString);
      var month = Number(monthString);
      var day = Number(dayString);
      var now = new Date();
      var nowYear = now.getFullYear();
      var nowMonth = now.getMonth() + 1;
      var nowDay = now.getDate();
      var date;
      if (year === nowYear && month === nowMonth && day === nowDay) {
        date = '오늘 ' + text.slice(11, 16);
      } else if (year === nowYear) {
        date = monthString + '/' + dayString;
      } else {
        date = text.slice(2, 4) + '/' + monthString + '/' + dayString;
      }
      return date;
    },
    formatRelativeDate: function (text) {
      if (!text) {
        return '';
      }
      var date;
      var now = _diffTime ? new Date(new Date().getTime() - _diffTime) : new Date();
      var target = new Date(text);
      var diff = (now.getTime() - target.getTime()) / 1000;
      if (diff < 120) {
        date = '방금';
      } else if (diff < 3600) {
        date = Math.floor(diff / 60) + '분 전';
      } else {
        var yearString = text.slice(2, 4);
        var monthString = text.slice(5, 7);
        var dayString = text.slice(8, 10);
        var timeString = text.slice(11, 16);
        date = monthString + '/' + dayString + ' ' + timeString;
        if (now.getFullYear() !== target.getFullYear()) {
          date = yearString + '/' + date;
        }
      }
      return date;
    },
    formatLink: function (text) {
      return text.replace(/(https?:\/\/[a-zA-Z0-9:&#@=_~%;?/.,+-]+)/g, '<a href="$1">$1</a>');
    },
    createBottomSheet: function (data) {
      var $bottomsheet = $('<div></div>').attr('id', 'bottomsheet');
      var $wrap = $('<div></div>').addClass('wrap').appendTo($bottomsheet);
      var $ol = $('<ol></ol>').appendTo($wrap);
      _.each(data, function (i) {
        var $li = $('<li></li>').data({
          id: i.id,
          value: i.value
        }).html(i.value).appendTo($ol);
        if (i.isSelected) {
          $li.addClass('active');
        }
      });
      $bottomsheet.on('click', function (event) {
        if (event.target !== event.currentTarget) {
          return;
        }
        $bottomsheet.remove();
      }).appendTo('body');
      return $bottomsheet;
    },
    createJpeg: function (base64data, callback) {
      var canvas = document.createElement('canvas');
      var ctx = canvas.getContext('2d');
      var img = new Image();
      img.onload = function () {
        canvas.width = img.width;
        canvas.height = img.height;
        ctx.drawImage(img, 0, 0, canvas.width, canvas.height);
        callback(canvas.toDataURL('image/jpeg', 0.8));
      };
      img.src = base64data;
    },
    createJpegThumb: function (base64data, callback) {
      var canvas = document.createElement('canvas');
      var ctx = canvas.getContext('2d');
      var oc = document.createElement('canvas');
      var octx = oc.getContext('2d');
      var img = new Image();
      img.onload = function () {
        var drawWidth = 240;
        var drawHeight = drawWidth * (img.height / img.width);
        canvas.width = drawWidth;
        canvas.height = (drawHeight > 342) ? 342 : drawHeight;
        oc.width = img.width * 0.5;
        oc.height = img.height * 0.5;
        octx.drawImage(img, 0, 0, oc.width, oc.height);
        octx.drawImage(oc, 0, 0, oc.width * 0.5, oc.height * 0.5);
        ctx.drawImage(oc, 0, 0, oc.width * 0.5, oc.height * 0.5, 0, 0, drawWidth, drawHeight);
        callback(canvas.toDataURL('image/jpeg', 0.8));
      };
      img.src = base64data;
    }
  },
  data: {
    activityTargetNames: [
      {id: 1, value: '공모전'},
      {id: 2, value: '대외활동'}
    ],
    activityCategoryNames: [
      {id: 101, value: '기획/아이디어'},
      {id: 102, value: '브랜드/네이밍'},
      {id: 103, value: '광고/마케팅'},
      {id: 104, value: '사진/영상/UCC'},
      {id: 105, value: '디자인'},
      {id: 106, value: '예체능'},
      {id: 107, value: '문학/수기/시나리오'},
      {id: 108, value: 'IT/소프트웨어/게임'},
      {id: 109, value: '기타'},
      {id: 201, value: '국내봉사'},
      {id: 202, value: '해외봉사/탐방'},
      {id: 203, value: '서포터즈'},
      {id: 204, value: '기자단'},
      {id: 205, value: '마케터'},
      {id: 206, value: '기타'}
    ],
    activityCompanyTypes: [
      {id: 1, value: '중앙정부/기관'},
      {id: 2, value: '지자체/기관'},
      {id: 3, value: '학교/재단/협회'},
      {id: 4, value: '비영리단체'},
      {id: 5, value: '공기업'},
      {id: 6, value: '일반기업'},
      {id: 7, value: '신문/방송/언론'},
      {id: 8, value: '기타'}
    ],
    activityPrizeTotals: [
      {id: 1, value: '5천만원이상'},
      {id: 2, value: '5천만원~3천만원'},
      {id: 3, value: '3천만원~1천만원'},
      {id: 4, value: '1천만원이하'}
    ],
    activityPrizeBenefits: [
      {id: 0, value: '입사가산점'},
      {id: 1, value: '인턴채용'},
      {id: 2, value: '정규직채용'},
      {id: 3, value: '해외연수'},
      {id: 4, value: '작품전시'},
      {id: 5, value: '기타'}
    ],
    clubCategoryNames: [
      {id: 1, value: '문화/예술/공연'},
      {id: 2, value: '봉사/사회활동'},
      {id: 3, value: '학술/교양'},
      {id: 4, value: '창업/취업'},
      {id: 5, value: '어학'},
      {id: 6, value: '체육'},
      {id: 7, value: '친목'},
      {id: 8, value: '기타'}
    ],
    clubLocalNames: [
      {id: 99, value: '전국'},
      {id: 1, value: '수도권'},
      {id: 3, value: '충북/충남/대전'},
      {id: 4, value: '전북'},
      {id: 5, value: '전남/광주'},
      {id: 6, value: '경북/대구'},
      {id: 7, value: '경남/부산/울산'},
      {id: 8, value: '강원'},
      {id: 9, value: '제주'},
      {id: 10, value: '기타'}
    ],
    clubSizes: [
      {id: 1, value: '10명 미만'},
      {id: 2, value: '10~30명'},
      {id: 3, value: '30~50명'},
      {id: 4, value: '50명 이상'}
    ],
    cultureCategoryNames: [
      {id: 1, value: '영화'},
      {id: 2, value: '공연'},
      {id: 3, value: '전시'},
      {id: 4, value: '축제'},
      {id: 5, value: '행사'},
      {id: 6, value: '기타'}
    ]
  }
};

$().ready(function () {
  $('#container').find('a[data-community-url]').each(function () {
    $(this).attr('href', cp.fn.getCommunityUrl($(this).data('community-url')));
  });
  $('#container').on('click', 'a[href]', function (event) {
    event.stopPropagation();
    if (typeof window.CampuspickWebview !== 'undefined') {
      event.preventDefault();
      var url = $(this).attr('href');
      cp.fn.goLink(url);
    }
  });
  if ($('#container').is(':has(input[type="date"])')) {
    var $dateInput = $('#container input[type="date"]');
    if ($dateInput.attr('type') !== $dateInput[0].type) {
      var placeholder = '예) ' + new Date().getFullYear().toString() + '-03-30';
      $dateInput.attr({
        placeholder: placeholder,
        maxlength: 10
      });
      $dateInput.on('change', function (e) {
        var value = $(this).val();
        if (!value) {
          return;
        }
        if (!new RegExp('^2([0-9]{3})-([0-1]{1})([0-9]{1})-([0-3]{1})([0-9]{1})$').test(value)) {
          alert('날짜 형식에 맞게 입력해주세요.\n예) 2017-03-30');
        }
      });
    }
  }
  if (typeof Storage !== 'undefined' && !$('body').hasClass('app')) {
    var currentpageCache = sessionStorage.getItem('currentpage');
    if (currentpageCache) {
      sessionStorage.setItem('previouspage', currentpageCache);
    }
    sessionStorage.setItem('currentpage', location.pathname + location.search);
  }
});