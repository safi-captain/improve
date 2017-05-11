<template>
  <div class="userInfo" v-on:click="backPre">
    <h1>员工姓名: {{ name }}</h1>
    <h1>员工年龄: {{ age }}</h1>
    <h1>员工性别: {{ sex }}</h1>
  </div>
</template>

<script>
export default {
  data () {
    return {
      name: 'safi',
      age: 25,
      sex: 'male'
    }
  },
  methods: {
    backPre () {
      console.log('arrive')
      this.$router.go(-1)
    }
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
