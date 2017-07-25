cp.activityrequestfree = {
  isSubmitted: false,
  imageData: '',
  imageThumbData: '',
  imageThumbDataTemp: '',
  clickAttach: function ($wrap) {
    if (typeof window.FileReader === 'undefined') {
      alert('파일 첨부를 위해 최신 브라우저를 이용해주세요.');
      return;
    }
    if ($wrap.find('input[name="attach_filename"]').val()) {
      if (confirm('첨부된 파일을 삭제하시겠습니까?')) {
        cp.activityrequestfree.resetAttach($wrap);
      }
    } else {
      $wrap.find('input[name="attach"]').click();
    }
  },
  resetAttach: function ($wrap) {
    if ($wrap.hasClass('thumbnail')) {
      cp.activityrequestfree.imageThumbData = '';
    } else {
      cp.activityrequestfree.imageData = '';
      cp.activityrequestfree.imageThumbDataTemp = '';
    }
    var $attach = $wrap.find('input[name="attach"]');
    var $clone = $attach.clone(true).insertAfter($attach);
    $attach.remove();
    $wrap.find('input[name="attach_filename"]').val('');
    $wrap.find('input[name="attach_button"]').val('파일 선택');
  },
  changeAttach: function (t, $wrap) {
    var file = t.files[0];
    if (!file || !file.type.match('image')) {
      alert('이미지만 첨부할 수 있습니다.');
      cp.activityrequestfree.resetAttach($wrap);
      return false;
    }
    var fileReader = new FileReader();
    fileReader.onload = function (e) {
      if ($wrap.hasClass('thumbnail')) {
        cp.fn.createJpegThumb(e.target.result, function (base64data) {
          cp.activityrequestfree.imageThumbData = base64data;
        });
      } else {
        cp.fn.createJpeg(e.target.result, function (base64data) {
          cp.activityrequestfree.imageData = base64data;
        });
        cp.fn.createJpegThumb(e.target.result, function (base64data) {
          cp.activityrequestfree.imageThumbDataTemp = base64data;
        });
      }
    };
    fileReader.readAsDataURL(file);
    $wrap.find('input[name="attach_filename"]').val(file.name);
    $wrap.find('input[name="attach_button"]').val('첨부 취소');
  },
  submit: function () {
    if (cp.activityrequestfree.isSubmitted) {
      return;
    }
    var managerName = cp.el['#container'].find('input[name="manager_name"]').val().trim();
    var managerPhone = cp.el['#container'].find('input[name="manager_phone"]').val();
    var managerEmail = cp.el['#container'].find('input[name="manager_email"]').val().trim();
    var managerCompany = cp.el['#container'].find('input[name="manager_company"]').val().trim();
    var title = cp.el['#container'].find('input[name="title"]').val().trim();
    var website = cp.el['#container'].find('input[name="website"]').val().trim();
    var target = cp.el['#container'].find('input[name="target"]:checked').val();
    var category = JSON.stringify(cp.el['category'].filter(':visible').find('input[name="category"]:checked').map(function () {
      return Number($(this).val());
    }).get());
    var startDate = cp.el['#container'].find('input[name="start_date"]').val();
    var endDate = cp.el['#container'].find('input[name="end_date"]').val();
    var company = cp.el['#container'].find('input[name="company"]').val().trim();
    var companyType = cp.el['#container'].find('input[name="company_type"]:checked').val();
    var company2 = cp.el['#container'].find('input[name="company2"]').val().trim();
    var company3 = cp.el['#container'].find('input[name="company3"]').val().trim();
    var prizeTop = cp.el['#container'].find('input[name="prize_top"]').val();
    var prizeTotal = cp.el['#container'].find('input[name="prize_total"]:checked').val();
    var prizeBenefit = JSON.stringify(cp.el['#container'].find('input[name="prize_benefit"]').map(function () {
      return Number($(this).is(':checked'));
    }).get());
    var description = cp.el['#container'].find('textarea[name="description"]').val().trim();
    var isAgreed = cp.el['#container'].find('input[name="agree"]').is(':checked');
    if (!managerName || !managerPhone || !managerEmail || !managerCompany) {
      alert('담당자 정보를 입력해주세요.');
      return false;
    } else if (!/^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/.test(managerEmail)) {
      alert('올바른 이메일을 입력해주세요.');
      return false;
    } else if (!title) {
      alert('제목을 입력해주세요.');
      return false;
    } else if (category === '[]') {
      alert('분야를 한 개 이상 선택해주세요.');
      return false;
    } else if (!startDate || !endDate) {
      alert('접수기간을 입력해주세요.');
      return false;
    } else if (isNaN(Date.parse(startDate)) || isNaN(Date.parse(endDate))) {
      alert('올바른 접수기간을 입력해주세요.');
      return false;
    } else if (new Date(startDate) >= new Date(endDate)) {
      alert('접수 시작일이 마감일보다 빨라야 합니다.');
      return false;
    } else if (((new Date(endDate) - new Date(startDate)) / 86400000) > 100) {
      alert('기간은 100일 이내로 지정해주세요.');
      return false;
    } else if (!company) {
      alert('주최 기관을 입력해주세요.');
      return false;
    } else if (cp.activityrequestfree.imageData === '') {
      alert('이미지를 첨부해주세요.');
      return false;
    } else if (!isAgreed) {
      alert('개인정보 수집 및 이용 안내에 동의해주세요.');
      return false;
    }
    cp.activityrequestfree.isSubmitted = true;
    if (website.length > 0 && !website.startsWith('http')) {
      website = 'http://' + website;
    }
    if (cp.activityrequestfree.imageThumbData === '' && cp.activityrequestfree.imageThumbDataTemp !== '') {
      cp.activityrequestfree.imageThumbData = cp.activityrequestfree.imageThumbDataTemp;
    }
    $.ajax({
      url: '/save/pageactivityrequest',
      type: 'POST',
      dataType: 'json',
      data: {
        managerName: managerName,
        managerPhone: managerPhone,
        managerEmail: managerEmail,
        managerCompany: managerCompany,
        title: title,
        website: website,
        target: target,
        category: category,
        startDate: startDate,
        endDate: endDate,
        company: company,
        companyType: companyType,
        company2: company2,
        company3: company3,
        prizeTop: prizeTop,
        prizeTotal: prizeTotal,
        prizeBenefit: prizeBenefit,
        description: description,
        imageData: cp.activityrequestfree.imageData,
        imageThumbData: cp.activityrequestfree.imageThumbData
      },
      success: function (response) {
        alert('게시 요청이 접수되었습니다.');
        cp.fn.goBack();
      }
    });
  }
};

$().ready(function () {
  cp.el['#container'] = $('#container');
  cp.el['category'] = cp.el['#container'].find('div.category');

  cp.el['#container'].find('input[name="target"]').on('change', function () {
    var target = Number($(this).val());
    cp.el['category'].hide();
    cp.el['category'].filter(function () {
      return $(this).data('targetfor') === target;
    }).show();
  });
  cp.el['#container'].on('click', 'input[name="attach_button"]', function () {
    var $wrap = $(this).parent('p.wrap');
    cp.activityrequestfree.clickAttach($wrap);
  });
  cp.el['#container'].on('change', 'input[name="attach"]', function () {
    var $wrap = $(this).parent('p.wrap');
    cp.activityrequestfree.changeAttach(this, $wrap);
  });
  cp.el['#container'].on('submit', function () {
    cp.activityrequestfree.submit();
    return false;
  });
});
