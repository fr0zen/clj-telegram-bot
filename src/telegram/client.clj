(ns telegram.client
  (:require  [clj-http.client :as client]
    [clojure.data.json :as json]))

(def api-url (str "https://api.telegram.org/bot"))

(defn- request [verb token data]
  (let [response (client/post (str api-url token "/" verb)
                   {:form-params data
                     :as :json})
         json     (json/read-str (:body response) :key-fn keyword)]
    json))

(defn get-me [token]
  (request "getMe" token nil))

(defn send-message [token chat-id text]
  (request "sendMessage" token (merge {:chat_id chat-id
                                        :text (clojure.string/join "\r\n" text)}))
  true)

(defn get-updates
  ([token] (request "getUpdates" token nil))
  ([token offset] (request "getUpdates" token {:offset offset})))

(defn process-update [token update commands]
  (let [m (:message update)
         chat-id (-> m :chat :id)
         text (-> m :text)
         update-id (:update_id update)
         [command string] (clojure.string/split text #" " 2)]
    (get-updates token (inc update-id)) ; marking updates as processed
    [chat-id (or (second (first (filter #(= command (first %)) commands))) ["command not found"])]))
