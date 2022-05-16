<?php

namespace App\Http\Controllers;

use App\Http\Requests\DemandeMaterielRequest;
use App\Models\DemandeMateriel;
use Illuminate\Http\Request;

class DemandeMaterielController extends Controller
{
    public function create(DemandeMaterielRequest $request){
        $dateEmploi = $request->input('dateEmploi');
        $dateDeRemise = $request->input('dateDeRemise');
        $idResponsableClub = $request->user()->id;
        $idDestinataire = $request->input('idDestinataire');
        $idCategorie = $request->input('idCategorie');
        $data = array(
            "idResponsableClub"=> $idResponsableClub,
            "idDestinataire"=> $idDestinataire,
            "dateEmploi"=> $dateEmploi,
            "dateDeRemise"=> $dateDeRemise,
            "idCategorie"=> $idCategorie,

        );
        $DemandeMateriel = DemandeMateriel::create($data);
        if ($DemandeMateriel) {
            return response()->json([
                'data' => [
                    'message' => 'Success',
                    'id' => $DemandeMateriel->id,
                    'attributes' => $DemandeMateriel
                ]
            ], 201);
        }
        return "Success";
    }
    public function show(Request $request){
        $DemandeMateriel = $request->user()->DemandeMateriel()->with('materiel')->with('destinataire')->paginate(5);
        if(!empty($DemandeMateriel)){
        return response()->json($DemandeMateriel, 200);
        }
        else
            return response()->json([
                "aucune demande"
            ], 404);
    }

    public function update(DemandeMaterielRequest $request ,$id){
        $DemandeMateriel = $request->user()->DemandeMateriel()->find($id);
        if($DemandeMateriel){
            $DemandeMateriel->idDestinataire= $request->input('idDestinataire')?$request->input('idDestinataire'):$DemandeMateriel->idDestinataire;
            $DemandeMateriel->dateEmploi= $request->input('dateEmploi')?$request->input('dateEmploi'):$DemandeMateriel->dateEmploi;
            $DemandeMateriel->dateDeRemise= $request->input('dateDeRemise')?$request->input('dateDeRemise'):$DemandeMateriel->dateDeRemise;
            if($request->input('idCategorie')!=$DemandeMateriel->idCategorie){

                $DemandeMateriel->idCategorie= $request->input('idCategorie')?$request->input('idCategorie'):$DemandeMateriel->idCategorie;
                $DemandeMateriel->materiel()->detach();
            }
            $DemandeMateriel->save();
            return response()->json([
                'message' => 'Update Success',
                'id' => $DemandeMateriel->id,
                'attributes' => $DemandeMateriel
        ], 201);
        }else{
            return response()->json([
                "demande non trouvée"
            ], 404);
    }
    }
    public function delete(Request $request,$id){
        $DemandeMateriel = $request->user()->DemandeMateriel()->find($id);
        if ($DemandeMateriel) {
            $DemandeMateriel->delete();
            return response()->json([
            ], 204);
        } else {
            return response()->json([
                'type' => 'DemandeMateriel',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
    public function getAllDemande(Request $request){
        $user = $request->user();
        $DemandeMateriel =DemandeMateriel::where('idDestinataire',$user->id)->with('materiel')->with('responsableClub')->paginate(5);

        if(!empty($DemandeMateriel)){
        return response()->json($DemandeMateriel, 200);
        }
        else
            return response()->json([
                "aucune demande"
            ], 404);
    }
    public function accept($id){
        $DemandeMateriel = DemandeMateriel::find($id);
        if ($DemandeMateriel) {
            $DemandeMateriel->status = 1;
            $DemandeMateriel->save();
            return response()->json('ChefDepartement accepted',200);
        } else {
            return response()->json([
                'type' => 'DemandeMateriel',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
    public function refuse($id){
        $DemandeMateriel = DemandeMateriel::find($id);
        if ($DemandeMateriel) {
            $DemandeMateriel->status = 2;
            $DemandeMateriel->save();
            return response()->json('ChefDepartement refused',200);;
        } else {
            return response()->json([
                'type' => 'DemandeMateriel',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }
    public function setReponse($id,Request $request){
        $DemandeMateriel = DemandeMateriel::find($id);
        if ($DemandeMateriel) {
            $DemandeMateriel->reponse = $request->input('reponse');
            $DemandeMateriel->save();
            return response()->json('Reponse ajouter avec succès',200);;
        } else {
            return response()->json([
                'type' => 'DemandeMateriel',
                'message' => 'demande non trouvée'
            ], 404);
        }
    }


}
