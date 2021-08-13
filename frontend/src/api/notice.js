import { instance } from './index'

function fetchMsg(data) {
  return instance.post('social/readAlarm', data)
}

export { fetchMsg }
