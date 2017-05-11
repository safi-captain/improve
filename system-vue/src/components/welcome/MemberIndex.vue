<template>
  <div class="userIndex">
    <h1 v-on:click="jump">{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  data () {
    return {
      msg: '欢迎进入员工页面'
    }
  },
  methods: {
    jump () {
      window.location.href = '#/index-member-detail'
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
