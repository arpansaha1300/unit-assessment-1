import { Outlet } from 'react-router-dom'

export default function DefaultLayout() {
  return (
    <div className="min-w-screen min-h-screen">
      <div className="min-w-screen min-h-screen flex flex-col relative z-20">
        <div className="flex-grow">
          <Outlet />
        </div>
      </div>
    </div>
  )
}
