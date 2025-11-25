// ===================== DỮ LIỆU MEGA MENU ===================== //
const MEGA_DATA = {
  store: {
    cols: [
      {
        title: 'Shop',
        items: [
          '<a href="#">Shop the Latest</a>',
          '<a href="/macbook-pro">Mac</a>',
          '<a href="/iphone17-pro">iPhone</a>',
          '<a href="/shop/ipad.html">iPad</a>',
          '<a href="watch-ultra">Apple Watch</a>',
          '<a href="#">Accessories</a>'
        ]
      },
      {
        title: 'Quick Links',
        items: [
          '<a href="#">Find a Store</a>',
          '<a href="#">Order Status</a>',
          '<a href="#">Apple Trade In</a>',
          '<a href="#">Financing</a>'
        ]
      },
      {
        title: 'Special Stores',
        items: [
          '<a href="#">Education</a>',
          '<a href="#">Business</a>',
          '<a href="#">Government</a>'
        ]
      }
    ]
  },

  mac: {
    cols: [
      {
        title: 'Explore Mac',
        items: [
          'MacBook Air',
          '<a href="/macbook-pro" class="hover:text-brand">MacBook Pro</a>',
          'iMac',
          'Mac mini',
          'Mac Studio',
          'Mac Pro'
        ]
      },
      {
        title: 'Accessories',
        items: [
          'Displays',
          'Keyboards',
          'Mice & Trackpads',
          'Adapters & Cables'
        ]
      },
      {
        title: 'Shop Mac',
        items: [
          '<a href="/shop/mac.html" class="hover:text-brand">Shop Mac</a>',
          '<a href="#">Compare Mac</a>',
          '<a href="#">Why Mac</a>',
          '<a href="#">Mac for Business</a>'
        ]
      }
    ]
  },

  ipad: {
    cols: [
      {
        title: 'Explore iPad',
        items: [
          'iPad Pro',
          'iPad Air',
          'iPad',
          'iPad mini'
        ]
      },
      {
        title: 'Accessories',
        items: [
          'Apple Pencil',
          'Keyboards',
          'Covers & Cases'
        ]
      },
      {
        title: 'Shop iPad',
        items: [
          '<a href="/shop/ipad.html" class="hover:text-brand">Shop iPad</a>',
          '<a href="#">Compare iPad</a>',
          '<a href="#">Why iPad</a>'
        ]
      }
    ]
  },

  iphone: {
    cols: [
      {
        title: 'Explore iPhone',
        items: [
          '<a href="/iphone17-pro" class="hover:text-brand">iPhone 17 Pro</a>',
          'iPhone 16',
          'iPhone 15',
          'iPhone SE'
        ]
      },
      {
        title: 'Accessories',
        items: [
          'Cases & Protection',
          'MagSafe',
          'Chargers & Cables'
        ]
      },
      {
        title: 'Shop iPhone',
        items: [
          '<a href="/shop/iphone" class="hover:text-brand">Shop iPhone</a>',
          '<a href="#">Compare iPhone</a>',
          '<a href="#">Switch to iPhone</a>'
        ]
      }
    ]
  },

  watch: {
    cols: [
      {
        title: 'Explore Watch',
        items: [
          'Apple Watch Series 10',
          '<a href ="/watch-ultra">Apple Watch Ultra 2</a>',
          'Apple Watch SE'
        ]
      },
      {
        title: 'Accessories',
        items: [
          'Bands',
          'Chargers',
          'All Accessories'
        ]
      },
      {
        title: 'Shop Watch',
        items: [
          '<a href="#">Shop Apple Watch</a>',
          '<a href="#">Compare Watch</a>',
          '<a href="#">Why Apple Watch</a>'
        ]
      }
    ]
  },

  airpods: {
    cols: [
      {
        title: 'Explore AirPods',
        items: [
          'AirPods Pro (2nd Gen)',
          'AirPods (3rd Gen)',
          'AirPods Max'
        ]
      },
      {
        title: 'Accessories',
        items: [
          'Cables & Chargers',
          'Audio Accessories'
        ]
      },
      {
        title: 'Shop AirPods',
        items: [
          '<a href="#">Shop AirPods</a>',
          '<a href="#">Compare AirPods</a>'
        ]
      }
    ]
  },

  tv: {
    cols: [
      {
        title: 'Explore TV & Home',
        items: [
          'Apple TV 4K',
          'HomePod',
          'Home app'
        ]
      },
      {
        title: 'Shop',
        items: [
          '<a href="#">Shop Apple TV 4K</a>',
          '<a href="#">Accessories</a>'
        ]
      },
      {
        title: 'Entertainment',
        items: [
          '<a href="#">Apple TV+</a>',
          '<a href="#">Apple Music</a>'
        ]
      }
    ]
  },

  support: {
    cols: [
      {
        title: 'Get Help',
        items: [
          'iPhone Support',
          'Mac Support',
          'iPad Support',
          'Watch Support'
        ]
      },
      {
        title: 'Repair & Warranty',
        items: [
          'Service & Repair',
          'Warranty',
          'AppleCare+'
        ]
      },
      {
        title: 'Resources',
        items: [
          'User Guides',
          'Community',
          'Contact Us'
        ]
      }
    ]
  },

  login: {
    cols: [
      {
        title: 'Tài khoản',
        items: [
          '<a href="/html/Login.html">Đăng nhập</a>',
          '<a href="/Register.html">Đăng ký</a>',
          '<a href="#">Quên mật khẩu</a>'
        ]
      },
      {
        title: 'Bảo mật',
        items: [
          '<a href="#">Chính sách bảo mật</a>',
          '<a href="#">Điều khoản sử dụng</a>'
        ]
      }
    ]
  }
};


document.addEventListener("DOMContentLoaded", () => {
  const observer = new IntersectionObserver((entries)=>{
    entries.forEach(e=>{
      if(e.isIntersecting) e.target.classList.add("show");
    });
  },{threshold:0.3});
  document.querySelectorAll(".fade-up, .feature-section").forEach(el=>observer.observe(el));

  // Parallax + video auto play
  const parallaxEls=document.querySelectorAll(".parallax");
  const heroVideo=document.querySelector("#hero video");

  window.addEventListener("scroll",()=>{
    const y=window.scrollY;
    parallaxEls.forEach(el=>el.style.setProperty("--scroll",y));
    if(heroVideo){
      const rect=heroVideo.getBoundingClientRect();
      if(rect.top<window.innerHeight && rect.bottom>0){
        heroVideo.play();
      }else{
        heroVideo.pause();
      }
    }
  });
});



// ===================== CÁC BIẾN CỐT LÕI ===================== //
const megaHost = document.getElementById('megaHost');
const nav = document.getElementById('globalNav');
const navItems = document.querySelectorAll('.nav-item');
let hideTimer = null;

// ===================== HÀM XÂY DỰNG HTML ===================== //
function buildMega(menuKey) {
  const data = MEGA_DATA[menuKey];
  if (!data) return '';

  const colsHTML = data.cols.map(col => `
    <div class="mega-col">
      <h4 class="text-gray-400 uppercase text-xs mb-3">${col.title}</h4>
      ${col.items.map(it => `<a href="#" class="block py-1 hover:text-brand transition">${it}</a>`).join('')}
    </div>
  `).join('');

  return `
    <div class="mega-panel show" role="dialog" aria-label="${menuKey} menu">
      <div class="mega-inner grid grid-cols-3 gap-12 max-w-[1200px] mx-auto p-8">
        ${colsHTML}
      </div>
    </div>`;
}




// ===================== HIỂN THỊ / ẨN MENU ===================== //
function showMega(menuKey) {
  clearTimeout(hideTimer);
  megaHost.innerHTML = buildMega(menuKey);
  megaHost.classList.add('opacity-100', 'pointer-events-auto');
  megaHost.classList.remove('opacity-0', 'pointer-events-none');
}

function hideMegaDelayed() {
  hideTimer = setTimeout(() => {
    megaHost.classList.add('opacity-0', 'pointer-events-none');
    megaHost.classList.remove('opacity-100', 'pointer-events-auto');
    setTimeout(() => (megaHost.innerHTML = ''), 150);
  }, 150);
}

// ===================== GẮN SỰ KIỆN ===================== //
navItems.forEach(item => {
  const key = item.dataset.menu;
  item.addEventListener('mouseenter', () => showMega(key));
  item.addEventListener('mouseleave', hideMegaDelayed);
});
megaHost.addEventListener('mouseenter', () => clearTimeout(hideTimer));
megaHost.addEventListener('mouseleave', hideMegaDelayed);

// ===================== MOBILE MENU ===================== //
const btnMenu = document.getElementById('btnMenu');
const mobileNav = document.getElementById('mobileNav');
btnMenu?.addEventListener('click', () => {
  const isHidden = mobileNav.classList.contains('hidden');
  mobileNav.classList.toggle('hidden');
  btnMenu.setAttribute('aria-expanded', String(isHidden));
});

// ===================== SEARCH (DEMO) ===================== //
const btnSearch = document.getElementById('btnSearch');
btnSearch?.addEventListener('click', () => {
  alert('🔍 Tính năng tìm kiếm sẽ được thêm sau');
});

//Hero ip16

const scrollWrapper = document.getElementById("scrollWrapper");
const scrollContent = scrollWrapper.querySelector(".scroll-content");
const slides = scrollContent.querySelectorAll(".hero-video");

const btnPrev = document.getElementById("btnPrev");
const btnNext = document.getElementById("btnNext");

let currentIndex = 0;

function updateSlider() {
  const offset = -currentIndex * scrollWrapper.offsetWidth;
  scrollContent.style.transform = `translateX(${offset}px)`;

  // Tạm dừng video không hiển thị
  slides.forEach((slide, i) => {
    if (slide.tagName === "VIDEO") {
      if (i === currentIndex) slide.play();
      else slide.pause();
    }
  });

  btnPrev.classList.toggle("hidden", currentIndex === 0);
  btnNext.classList.toggle("hidden", currentIndex === slides.length - 1);
}

btnNext.addEventListener("click", () => {
  if (currentIndex < slides.length - 1) currentIndex++;
  updateSlider();
});

btnPrev.addEventListener("click", () => {
  if (currentIndex > 0) currentIndex--;
  updateSlider();
});

window.addEventListener("resize", updateSlider);
updateSlider();


// Tối ưu FPS
function smoothSlide(targetX) {
  let currentX = 0;
  function animate() {
    currentX += (targetX - currentX) * 0.08;
    scrollContent.style.transform = `translateX(${currentX}px)`;
    if (Math.abs(targetX - currentX) > 0.5) requestAnimationFrame(animate);
  }
  requestAnimationFrame(animate);
}

