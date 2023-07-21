<script setup>

</script>

<template>
    <div class="w-full h-full overflow-y-scroll bg-slate-500">
        <div class="max-w-xl mx-auto">
            <!-- 공통 레이아웃 작성 부분 -->
            <slot /> <!--다른애들 자식으로-->
        </div>
    </div>
</template>