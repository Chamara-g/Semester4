Bid Server Supports multiple clients.
Step 1: Start by running Main file.
Step 2: Connect to server using nc to port 2000.
Step 3: Provide name, then symbol. Follow instructions to bid.

Server:
To view the prices of 6 major companies see GUI.
The text area at the center shows all bids made.
Fill the text area at the lower part with a symbol and press filter button to filter out specific companies.
All the changes made during a session is written to log.txt.
Note that at the end of the session note of the changes are committed, so stocks.csv remains the same

Client:
Bid can be made as mentioned in instructions.
To exit from current item and bid for a new item enter 'quit'.
To exit bidding session enter 'quit' again.
You'll be notified if another user bids for an item to which you're already connected.
