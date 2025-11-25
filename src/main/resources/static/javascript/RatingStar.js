<script>
    // ⭐ Hiệu ứng chọn sao trực quan
    document.addEventListener("DOMContentLoaded", function () {
      const stars = document.querySelectorAll("#rating-stars span");
      const ratingInput = document.getElementById("rating");

      stars.forEach(star => {
        star.addEventListener("mouseover", () => {
          const val = star.getAttribute("data-value");
          stars.forEach(s => s.style.color = s.getAttribute("data-value") <= val ? "#facc15" : "#d1d5db");
        });

        star.addEventListener("click", () => {
          const val = star.getAttribute("data-value");
          ratingInput.value = val;
          stars.forEach(s => s.style.color = s.getAttribute("data-value") <= val ? "#facc15" : "#d1d5db");
        });

        star.addEventListener("mouseout", () => {
          const val = ratingInput.value;
          stars.forEach(s => s.style.color = s.getAttribute("data-value") <= val ? "#facc15" : "#d1d5db");
        });
      });
    });
</script>

<script>
    document.addEventListener("DOMContentLoaded", function () {
      const thumbs = document.querySelectorAll(".thumb");
      const mainImage = document.getElementById("mainImage");
      const prevBtn = document.getElementById("prevBtn");
      const nextBtn = document.getElementById("nextBtn");
      let currentIndex = 0;

      const thumbList = Array.from(thumbs);
      function showImage(index) {
        const src = thumbList[index].getAttribute("src");
        mainImage.src = src;
        thumbList.forEach(t => t.classList.remove("border-blue-500"));
        thumbList[index].classList.add("border-blue-500");
      }

      thumbs.forEach((thumb, i) => {
        thumb.addEventListener("click", () => {
          currentIndex = i;
          showImage(currentIndex);
        });
      });

      prevBtn.addEventListener("click", () => {
        currentIndex = (currentIndex - 1 + thumbList.length) % thumbList.length;
        showImage(currentIndex);
      });

      nextBtn.addEventListener("click", () => {
        currentIndex = (currentIndex + 1) % thumbList.length;
        showImage(currentIndex);
      });

      showImage(0);
    });
</script>
<script>
    document.addEventListener("DOMContentLoaded", () => {
      document.querySelectorAll(".price").forEach(el => {
        const val = parseInt(el.textContent.replace(/[^\d]/g, ""));
        el.textContent = val.toLocaleString("vi-VN", { style: "currency", currency: "VND" });
      });
    });
</script>