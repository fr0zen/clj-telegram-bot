# clj-telegram-bot
A wrapper for Telegram Bot API using long pooling


## Usage

In a REPL:

```clojure
(use 'telegram.client)

(def commands [["/help" "help"]])

(def req (telegram.client/get-updates "YOUR:API_KEY"))

(def msgs (map #(telegram.client/process-update "YOUR:API_KEY" % commands) (req :result)))

(map #(telegram.client/send-message "YOUR:API_KEY" (first %) (second %)) msgs)
```


Inspired by https://github.com/thbkrshw/clj-telegram
