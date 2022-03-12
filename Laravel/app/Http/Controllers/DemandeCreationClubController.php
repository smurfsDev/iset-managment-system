<?php

namespace App\Http\Controllers;

use App\Http\Requests\DemandeCreationClubRequest;
use App\Models\DemandeCreationClub;
use Illuminate\Http\Request;

class DemandeCreationClubController extends Controller
{
    public function show()
    {
        $demandes = DemandeCreationClub::paginate(5);
        if (sizeof($demandes)>0)
            return response()->json(
                $demandes,
                200
            );
        else
            return response()->json([
                "aucune demande"
            ], 404);
    }
    public function showMyDemandes($id)
    {
        $demandes = DemandeCreationClub::where('responsableClubId','=',$id)->paginate(5);
        if (sizeof($demandes)>0)
            return response()->json(
                $demandes,
                200
            );
        else
            return response()->json([
            ], 404);
    }
    public function create(DemandeCreationClubRequest $request){
        $nomClub = $request->input('nomClub');
        $logo = $request->input('logo');
        $dateCreation = $request->input('dateCreation');
        $activite = $request->input('activite');
        $president = $request->input('president');
        $vicePresident = $request->input('vicePresident');
        $responsableClubId = $request->input('responsableClubId');
        $data = array(
            "nomClub"=> $nomClub,
            "logo"=> $logo,
            "dateCreation"=> $dateCreation,
            "activite"=> $activite,
            "president"=> $president,
            "vicePresident"=> $vicePresident,
            "responsableClubId"=> $responsableClubId,
        );
        $DemandeCreationClub = DemandeCreationClub::create($data);
        if ($DemandeCreationClub) {
            return response()->json([
                'data' => [
                    'message' => 'Success',
                    'id' => $DemandeCreationClub->id,
                    'attributes' => $DemandeCreationClub
                ]
            ], 201);
        }
        return "Success";
    }

    public function update(Request $request,$id){
        $DemandeCreationClub = DemandeCreationClub::find($id);
        if ($DemandeCreationClub){
            $DemandeCreationClub->nomClub = $request->input('nomClub')?$request->input('nomClub'):$DemandeCreationClub->nomClub;
            $DemandeCreationClub->logo = $request->input('logo')?$request->input('logo'):$DemandeCreationClub->logo;
            $DemandeCreationClub->dateCreation = $request->input('dateCreation')?$request->input('dateCreation'):$DemandeCreationClub->dateCreation;
            $DemandeCreationClub->activite = $request->input('activite')?$request->input('activite'):$DemandeCreationClub->activite;
            $DemandeCreationClub->president = $request->input('president')?$request->input('president'):$DemandeCreationClub->president;
            $DemandeCreationClub->vicePresident = $request->input('vicePresident')?$request->input('vicePresident'):$DemandeCreationClub->vicePresident;
            $DemandeCreationClub->save();
            return response()->json([
                    'message' => 'Update Success',
                    'id' => $DemandeCreationClub->id,
                    'attributes' => $DemandeCreationClub
            ], 201);
        }else{
            return response()->json([
                "demande non trouvée"
            ], 404);
        }
    }
    public function delete($id){
        $DemandeCreationClub = DemandeCreationClub::find($id);
        if ($DemandeCreationClub) {
            $DemandeCreationClub->delete();
            return response()->json([
            ], 204);
        } else {
            return response()->json([
                'type' => 'DemandeCreationClub',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
}
