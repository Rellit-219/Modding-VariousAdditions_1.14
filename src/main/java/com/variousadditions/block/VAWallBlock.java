package com.variousadditions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.block.WallBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class VAWallBlock extends WallBlock {

	public VAWallBlock(Properties properties) {
		super(properties);
		
	}
	
	private boolean func_220113_a(BlockState p_220113_1_, boolean p_220113_2_, Direction p_220113_3_) {
		Block block = p_220113_1_.getBlock();
		boolean flag = block.isIn(BlockTags.WALLS) || block instanceof FenceGateBlock && FenceGateBlock.isParallel(p_220113_1_, p_220113_3_) || block == this;
		return !cannotAttach(block) && p_220113_2_ || flag;
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext context) {
		
		IWorldReader iworldreader = context.getWorld();
		BlockPos blockpos = context.getPos();
		IFluidState ifluidstate = context.getWorld().getFluidState(context.getPos());
		
		BlockPos blockpos1 = blockpos.north();
		BlockPos blockpos2 = blockpos.east();
		BlockPos blockpos3 = blockpos.south();
		BlockPos blockpos4 = blockpos.west();
		
		BlockState blockstate = iworldreader.getBlockState(blockpos1);
		BlockState blockstate1 = iworldreader.getBlockState(blockpos2);
		BlockState blockstate2 = iworldreader.getBlockState(blockpos3);
		BlockState blockstate3 = iworldreader.getBlockState(blockpos4);
		
		boolean flag = this.func_220113_a(blockstate, blockstate.func_224755_d(iworldreader, blockpos1, Direction.SOUTH), Direction.SOUTH);
		boolean flag1 = this.func_220113_a(blockstate1, blockstate1.func_224755_d(iworldreader, blockpos2, Direction.WEST), Direction.WEST);
		boolean flag2 = this.func_220113_a(blockstate2, blockstate2.func_224755_d(iworldreader, blockpos3, Direction.NORTH), Direction.NORTH);
		boolean flag3 = this.func_220113_a(blockstate3, blockstate3.func_224755_d(iworldreader, blockpos4, Direction.EAST), Direction.EAST);
		boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);
		
		return this.getDefaultState().with(UP, Boolean.valueOf(flag4 || !iworldreader.isAirBlock(blockpos.up()))).with(NORTH, Boolean.valueOf(flag)).with(EAST, Boolean.valueOf(flag1)).with(SOUTH, Boolean.valueOf(flag2)).with(WEST, Boolean.valueOf(flag3)).with(WATERLOGGED, Boolean.valueOf(ifluidstate.getFluid() == Fluids.WATER));
	
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		
		if (stateIn.get(WATERLOGGED)) {
			
			worldIn.getPendingFluidTicks().scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
			
		}
		
		if (facing == Direction.DOWN) {
			
			return super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
			
		} else {
			
			Direction direction = facing.getOpposite();
			
			boolean flag = facing == Direction.NORTH ? this.func_220113_a(facingState, facingState.func_224755_d(worldIn, facingPos, direction), direction) : stateIn.get(NORTH);
			boolean flag1 = facing == Direction.EAST ? this.func_220113_a(facingState, facingState.func_224755_d(worldIn, facingPos, direction), direction) : stateIn.get(EAST);
			boolean flag2 = facing == Direction.SOUTH ? this.func_220113_a(facingState, facingState.func_224755_d(worldIn, facingPos, direction), direction) : stateIn.get(SOUTH);
			boolean flag3 = facing == Direction.WEST ? this.func_220113_a(facingState, facingState.func_224755_d(worldIn, facingPos, direction), direction) : stateIn.get(WEST);
			boolean flag4 = (!flag || flag1 || !flag2 || flag3) && (flag || !flag1 || flag2 || !flag3);
			
			return stateIn.with(UP, Boolean.valueOf(flag4 || !worldIn.isAirBlock(currentPos.up()))).with(NORTH, Boolean.valueOf(flag)).with(EAST, Boolean.valueOf(flag1)).with(SOUTH, Boolean.valueOf(flag2)).with(WEST, Boolean.valueOf(flag3));
		
		}
		
	}
	
}