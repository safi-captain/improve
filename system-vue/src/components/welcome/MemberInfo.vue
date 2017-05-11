<template>
  <div class="userInfo" v-on:click="backPre">
    <h1>会员姓名: {{ name }}</h1>
    <h1>会员年龄: {{ age }}</h1>
    <h1>会员性别: {{ sex }}</h1>
    <input v-model="ppap">
    <hr>
    <h3 :class="ppap">{{ ppap }}</h3>
    <hr>
    <welcomeIndex v-show="showCase"></welcomeIndex>
    <hr>
  </div>
</template>

<script>
export default {
  data () {
    return {
      name: 'nancy',
      age: 22,
      sex: 'female',
      showCase: false,
      ppap: ''
    }
  },
  methods: {
    backPre () {
      this.showCase = true
    }
  },
  components: {
    welcomeIndex: () => import('components/welcome/WelcomeIndex')
  }
}
</script>

<style scoped>
h1, h2 {
  font-weight: normal;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
