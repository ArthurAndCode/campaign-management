import reactLogo from './assets/react.svg'
import './App.css'
import { CampaignListPage } from './pages/campaignList'
import { Route, Routes } from 'react-router-dom'
import NotFound from './pages/notFound'

function App() {
  return (
    <>
      <div className='App'>
        <header className='App-header'>
          <img src={reactLogo} className='App-logo' alt='logo' />
          <h1 className='title'>Campaign Management</h1>
        </header>
        <Routes>
          <Route path="/" element={<CampaignListPage />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
        {/* <Menu user={user} setUser={setUser} /> */}
        {/* <PageContainer user={user} setUser={setUser} /> */}
        {/* <Footer /> */}
      </div>
    </>
  )
}

export default App
