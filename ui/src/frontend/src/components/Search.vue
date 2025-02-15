<template>

  <br/>
  <br/>
  <div class="container-fluid">
    <div class="row justify-content-center">
      <div class="col-2"/>
      <div id="mySidebar" class="sidebar">
        <a href="javascript:void(0)" class="closebtn" v-on:click="closeNav">×</a>
        <div class="pt-5 mt-5" style="text-align: center">
          <div v-if="selectedOffer">
            <SelectedOffer :offer="currentOffer"/>
          </div>
        </div>
      </div>

      <div class="col">
        <!-- Search engine -->
        <div class="align-middle">
          <div class="pr-5 pl-5 pt-2 pb-2">
            <input
                class="form-control text-center mr-sm-2 w-100 input-lg"
                type="search"
                placeholder="Type to search specific course"
                aria-label="Search"
                v-model="userFilter"
                v-on:input="filterOffers"/>
          </div>
        </div>

        <br/>
        <br/>
        <br/>

        <!-- Categories -->
        <Categories @categoryChanged="filterByCategory"/>

        <br/>

        <div class="container-fluid">
          <div class="row no-gutters">
            <div class="col" v-for="offer in showOffers" :key="offer.id">
              <OfferCard :offer="offer" @onSelected="OnSelectedOffer"/>
            </div>
          </div>
        </div>

      </div>
      <div class="col-2">
        <Account/>
      </div>
    </div>
  </div>

</template>

<script>
import Categories from "@/components/Categories";
import OfferCard from "@/components/OfferCard";
import SelectedOffer from "@/components/SelectedOffer";
import Account from "@/components/account/Account";


export default {
  name: 'Search',
  components: {
    Account,
    SelectedOffer,
    OfferCard,
    Categories
  },


  data() {
    return {
      selectedOffer: '',
      offers: [],
      showOffers: [],
      checkedCategories: [],
      userFilter: ''
    }
  },

  computed: {
    currentOffer() {
      return this.selectedOffer;
    }
  },

  async mounted() {
    fetch("api/offers/all" + this.userFilter)
        .then((response) => response.json())
        .then((data) => {
          this.offers = data;
          this.showOffers = this.getRandom(data, 12);
        });

    let allCat = await this.$store.getters.getAllCategories;
    this.checkedCategories = allCat.map(c => c.name);
  },

  methods: {
    getRandom(arr, n) {
      let result = new Array(n),
          len = arr.length,
          taken = new Array(len);
      if (n > len)
        return arr;

      while (n--) {
        var x = Math.floor(Math.random() * len);
        result[n] = arr[x in taken ? taken[x] : x];
        taken[x] = --len in taken ? taken[len] : len;
      }

      return result;
    },

    filterOffers() {
      // This needs some polling so that we do not query the database with every stroke
      // From how I understand this now, all the offers are cached in the browser and the filtering is done at client's side
      // I will leave this to you
      this.filterAll();
    },

    filterByCategory(categories) {
      this.checkedCategories = categories;
      this.filterAll();
    },

    filterAll() {
      let byCategory = this.offers.filter(d => this.checkedCategories.includes(d.category.name));
      let filtered = byCategory.filter(d => d.title.toLowerCase().includes(this.userFilter.toLowerCase()));
      this.showOffers = this.getRandom(filtered, 12);
    },

    OnSelectedOffer(offer) {
      this.closeNav();
      this.selectedOffer = offer;
      console.log("selected " + this.selectedOffer);
      this.openNav();
    },


    openNav() {

      document.getElementById("mySidebar").style.width = "100%";
    },

    closeNav() {
      document.getElementById("mySidebar").style.width = "0";
    }
  }

}
</script>

<style scoped>
.sidebar {
  height: 100%;
  width: 0;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: rgba(17, 17, 17, 0.93);
  overflow-x: hidden;
  transition: 0.5s;
  padding-top: 60px;
}

.sidebar a {
  padding: 8px 8px 8px 32px;
  text-decoration: none;
  font-size: 25px;
  color: #818181;
  display: block;
  transition: 0.3s;
}

.sidebar a:hover {
  color: #f1f1f1;
}

.sidebar .closebtn {
  position: absolute;
  top: 0;
  right: 25px;
  font-size: 36px;
  margin-left: 50px;
}

#main {
  transition: margin-left .5s;
  padding: 16px;
}

/* On smaller screens, where height is less than 450px, change the style of the sidenav (less padding and a smaller font size) */
@media screen and (max-height: 450px) {
  .sidebar {
    padding-top: 15px;
  }

  .sidebar a {
    font-size: 18px;
  }
}
</style>