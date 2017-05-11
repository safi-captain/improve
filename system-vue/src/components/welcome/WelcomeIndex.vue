<template>
  <div class="welcome-index" v-on:click="jump">
    <h1>{{ msg }}</h1>
  </div>
</template>

<script>
export default {
  data () {
    return {
      msg: 'welcome to welcomeIndex'
    }
  },
  methods: {
    jump () {
      window.location.href = '#/user/111405131/info'
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
