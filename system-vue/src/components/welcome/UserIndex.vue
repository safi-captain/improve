<template>
  <div class="userIndex">
    <h1 v-on:click="jump">{{ msg }}</h1>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  data () {
    return {
      msg: this.$route.params.userId
    }
  },
  methods: {
    jump () {
      window.location.href = '#/index-member'
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
