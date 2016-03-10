# clj-telegram-bot
A wrapper for Telegram Bot API using long pooling


## Usage

In a REPL:

```clojure
(def req (telegram.client/get-updates "YOUR:API_KEY"))

(def msgs (map #(telegram.client/process-update "YOUR:API_KEY" %) (req :result)))

(map #(telegram.client/send-message "YOUR:API_KEY" (first %) (second %)) msgs)
```
