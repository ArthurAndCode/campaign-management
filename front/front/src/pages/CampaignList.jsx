import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../util/client';

export function CampaignListPage(props) {

    const [campaigns, setCampaigns] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchData = async () => {
            try {
                const response = await axiosInstance.get('/v1/campaigns');
                setCampaigns(response.data.content)
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        };

        fetchData();
    },
        //  [page, sortBy, sortOrder, selectedColors, selectedPatternValues, selectedPatternThemes, searchText]
    );

    return (
        <div className="campaigns-page-content">
            <div className="campaign-content">
                <div className={'campaign-list'}>
                    {campaigns.map((campaign) => {
                        return (
                            <div
                                key={campaign.id}
                                className="campaign-card"
                                onClick={() => {
                                    navigate(`/campaigns/details`, { state: { campaign } });
                                }}
                            >
                                <h4>Campaign Name: {campaign.campaignName}</h4>
                                <h4>Name: {campaign.campaignName}</h4>
                                <h4>Name: {campaign.campaignName}</h4>
                            </div>
                        );
                    }
                    )}
                </div>
            </div>
        </div>
    );
}