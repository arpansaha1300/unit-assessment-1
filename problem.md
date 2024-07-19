Next is a new Video Streaming Service Provider, who sells popular individual shows of each platform as his own by purchasing rights from Prime Video, Netflix, Disney Plus.

Next came to Amdocs and asked us to:
- Build a Catalog REST which will contain popular shows and price of each show and multiple images of show.
- The data from Catalog will come from Prime Video, Netflix & Disney Plus API’s, the response from Vendors contain vendor price (Price at which Netflix/Amazon Prime/Disney Plus selling to Next).
If the same show can appear across multiple vendors, we must choose the one which has lower vendor price.
In response of ReST we need to calculate the customer price, as vendor price + 50% profit for 5 star rating and reduce 10% for each reducing rate.
- Example: If Good doctor at Netflix costs 1USD and is 5star rated show, then we must return price to customer as 1.50 USD.

UI:
-	Create Card Layout
-	Create Responsive Design

Expectations:
[x]	Please design the API using Swagger 2.0
[x]	Develop the ReST using Spring Boot.
[x]	Create your own response same structure of Vendor.
[x] When you click on a particular show, show Image gallery with multiple images – along with all other details of the Show 
[x] Add a search bar to search a show, list shows for every letter typed – if no response, show ‘no match found’ message
[x] Dropdown under search
[x]	Think about caching of vendor data, it will be refreshed every day at 00:15 hrs of (UTC)
[x] Responsive design
