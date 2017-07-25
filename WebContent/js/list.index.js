/*
 * @ 프로젝트명 : 캠거루
 * @ 패키지명 : /WebContent/js/
 * @ 파일명 : duplicator.join.js
 * @ 작성자 : 이재민(jaeminstar@naver.com)
 * @ 작성일 : 2017.4.28
 * @ 설명 : 메인페이지 진입시 각 게시판에 최신글들 리스트로 보여줌
 */


cp.pageindex = {
  init: function () {
	//커뮤니티
	cp.pageindex.loadCommunities(function (result) {
		if (!result) {
	    	return;
		}
		cp.pageindex.appendCommunities(result.communities);
	});
	
	//공모전
	cp.pageindex.loadContests(function (result) {
		if (!result) {
			return;
		}
		cp.pageindex.appendContests(result.contests);
	});		  

	//대외활동
	cp.pageindex.loadActivities(function (result) {
		if (!result) {
			return;
		}
		cp.pageindex.appendActivities(result.activities);
	});	 
	
	//동아리
    cp.pageindex.loadClubRecruits(function (result) {
      if (!result) {
        return;
      }
      cp.pageindex.appendClubRecruits(result.recruits);
    });
  },
  //D-DAY 나타내기
  getDday: function (endDate) {
	    var ddayCount = Math.floor((new Date().getTime() - new Date(endDate).getTime()) / 86400000) * -1;
	    var ddayValue;
	    if (ddayCount > 0) {
	      ddayValue = 'D-' + ddayCount.toString();
	    } else if (ddayCount === 0) {
	      ddayValue = 'D-DAY';
	    } else {
	      ddayValue = '마감';
	    }
	    return {
	      count: ddayCount,
	      value: ddayValue
	    };
	  },
	  loadCommunities: function (callback) {
	    $.ajax({
	      url: 'CommunityIndex',
	      type: 'POST',
	      dataType: 'json',
	      success: function (response) {
	          callback(response);
	      }
	    });
	  },
	  appendCommunities: function (communities) {
	    var $myCommunities, $suggstedCommunities;
	    if (_isLogged) {
	      $('<h2></h2>').text('내 커뮤니티').appendTo(cp.el['leftside']);
	      $myCommunities = $('<ol></ol>').addClass('communities').appendTo(cp.el['leftside']);
	    }
	    $('<h2></h2>').text('커뮤니티 둘러보기').appendTo(cp.el['leftside']);
	    $suggstedCommunities = $('<ol></ol>').addClass('communities').appendTo(cp.el['leftside']);
	    _.each(communities, function (community) {
	      if ([2570, 2571, 2572, 2605].indexOf(community.id) !== -1) {
	        return;
	      }
	      var url = 'http://localhost:8090/Kamgaru/Community/?comcode=' + community.id;
	      var pickCount = cp.fn.formatNumber(community.pickCount);
	      var $li = $('<li></li>');
	      var $a = $('<a></a>').addClass('community').attr('href', url).appendTo($li);
	      var $figure = $('<figure></figure>').appendTo($a);
	      if (community.picture !== '') {
	        $('<img>').attr('src', community.picture).on('load', function () {
	          $figure.css('background-image', 'url("' + community.picture + '")');
	        });
	      }
	      $('<p></p>').addClass('name').html(community.name).appendTo($a);
	      if (community.hasNewArticle === true) {
	        $('<img>').addClass('new').attr('src', '/images/community.new.png').appendTo($a);
	      }
	      $('<hr>').appendTo($a);
	      var $details = $('<p></p>').addClass('details').appendTo($a);
	      $('<span></span>').addClass('pick').html(pickCount).appendTo($details);
	      if (community.isPicked !== true) {
	        $('<span></span>').addClass('category').html(community.categoryName).appendTo($details);
	        if (community.info !== '') {
	          $('<p></p>').addClass('description').html(community.info).appendTo($a);
	        }
	      }
	      if (community.isPicked === true) {
	        $li.appendTo($myCommunities);
	      } else {
	        if ($suggstedCommunities.find('li').length < 6) {
	          $li.appendTo($suggstedCommunities);
	        }
	      }
	    });
	    if ($myCommunities && $myCommunities.is(':empty')) {
	      $('<li></li>').addClass('empty').html('관심있는 커뮤니티를 PICK하고,<br>다른 대학생들과 대화를 나누어 보세요!').appendTo($myCommunities);
	    }
	    var $moreCommunityLi = $('<li></li>').addClass('buttons').appendTo($suggstedCommunities);
	    $('<a></a>').addClass('button').text('다른 커뮤니티 더 보기').attr('href', 'http://localhost:8090/Kamgaru/Community/comlist.kam').appendTo($moreCommunityLi);
	  },
  //공모전
  loadContests: function (callback) {
    $.ajax({
      url: 'ContestIndex',
      type: 'POST',
      dataType: 'json',
      data: {
        limit: 6
      },
      success: function (response) {
          callback(response);
      }
    });
  },
  appendContests: function (contests) {
	    var $recent = cp.el['recent'].filter(function () {
	        return $(this).data('id') === 2570;
    });
    var $items = $recent.find('> ol.items');
    $recent.find('div.loading').remove();
    _.each(contests, function (contest) {
      var url = 'http://localhost:8090/Kamgaru/ContestBoard/contestboardcontent.kam?boardid=' + contest.id;
      var dday = cp.pageindex.getDday(contest.endDate);
      var viewCount = cp.fn.formatNumber(contest.viewCount);
      var $li = $('<li></li>');
      var $a = $('<a></a>').addClass('item').attr('href', url).appendTo($li);
      var $dday = $('<span></span>').addClass('dday').text(dday.value).appendTo($a);
      if (dday.count >= 0 && dday.count <= 10) {
        $dday.addClass('active');
      }
      $('<h3></h3>').html(contest.title).appendTo($a);
      $('<hr>').appendTo($a);
      $('<span></span>').addClass('viewcount').html(viewCount).appendTo($a);
      $('<span></span>').addClass('detail').html(contest.company).appendTo($a);
      $li.appendTo($items);
    });
  },
  //대외활동
  loadActivities: function (callback) {
    $.ajax({
      url: 'ActivityIndex',
      type: 'POST',
      dataType: 'json',
      data: {
        limit: 6
      },
      success: function (response) {
          callback(response);
      }
    });
  },
  appendActivities: function (activities) {
	    var $recent = cp.el['recent'].filter(function () {
	        return $(this).data('id') === 2571;
    });
    var $items = $recent.find('> ol.items');
    $recent.find('div.loading').remove();
    _.each(activities, function (activity) {
      var url = 'http://localhost:8090/Kamgaru/ActivityBoard/activityboardcontent.kam?boardid=' + activity.id;
      var dday = cp.pageindex.getDday(activity.endDate);
      var viewCount = cp.fn.formatNumber(activity.viewCount);
      var $li = $('<li></li>');
      var $a = $('<a></a>').addClass('item').attr('href', url).appendTo($li);
      var $dday = $('<span></span>').addClass('dday').text(dday.value).appendTo($a);
      if (dday.count >= 0 && dday.count <= 10) {
        $dday.addClass('active');
      }
      $('<h3></h3>').html(activity.title).appendTo($a);
      $('<hr>').appendTo($a);
      $('<span></span>').addClass('viewcount').html(viewCount).appendTo($a);
      $('<span></span>').addClass('detail').html(activity.company).appendTo($a);
      $li.appendTo($items);
    });
  },
  //동아리
  loadClubRecruits: function (callback) {
    $.ajax({
      url: 'GroupIndex',
      type: 'POST',
      dataType: 'json',
      data: {
        limit: 6
      },
      success: function (response) {
          callback(response);
      }
    });
  },
  appendClubRecruits: function (recruits) {
    var $recent = cp.el['recent'].filter(function () {
      return $(this).data('id') === 2572;
    });
    var $items = $recent.find('> ol.items');
    $recent.find('div.loading').remove();
    _.each(recruits, function (recruit) {
      var url = 'http://localhost:8090/Kamgaru/GroupBoard/GroupBoardView.kam?boardid=' + recruit.id;
      var dday = cp.pageindex.getDday(recruit.endDate);
      var viewCount = cp.fn.formatNumber(recruit.viewCount);
      var $li = $('<li></li>');
      var $a = $('<a></a>').addClass('item').attr('href', url).appendTo($li);
      var $dday = $('<span></span>').addClass('dday').text(dday.value).appendTo($a);
      if (dday.count >= 0 && dday.count <= 10) {
        $dday.addClass('active');
      }
      var $profile = $('<p></p>').addClass('profile').appendTo($a);
      var $picture = $('<figure></figure>').addClass('picture').appendTo($profile);
      if (recruit.clubImage !== '') {
        $('<img>').attr('src', recruit.clubImage).on('load', function () {
          $picture.css('background-image', 'url("' + recruit.clubImage + '")');
        });
      }
      $('<span></span>').addClass('name').html(recruit.clubName).appendTo($profile);
      $('<hr>').appendTo($a);
      $('<span></span>').addClass('viewcount').html(viewCount).appendTo($a);
      $('<h3></h3>').html(recruit.title).appendTo($a);
      $li.appendTo($items);
    });
  },
};
$(document).ready(function () {
	  cp.el['#container'] = $('#container');
	  cp.el['leftside'] = cp.el['#container'].find('section.leftside');
	  cp.el['center'] = cp.el['#container'].find('section.center');
	  cp.el['recent'] = cp.el['center'].find('div.recent');
	  cp.el['cards'] = cp.el['center'].find('div.cards');
	  cp.pageindex.init();
	});

